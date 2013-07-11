/*
 * Copyright 2013 Guidewire Software, Inc.
 */

package gw.plugin.ij.refactor.intoduceField;

import com.intellij.codeInsight.AnnotationUtil;
import com.intellij.codeInsight.ChangeContextUtil;
import com.intellij.codeInsight.TestFrameworks;
import com.intellij.codeInsight.highlighting.HighlightManager;
import com.intellij.codeInsight.lookup.LookupManager;
import com.intellij.codeInsight.navigation.NavigationUtil;
import com.intellij.ide.util.DirectoryChooserUtil;
import com.intellij.ide.util.PackageUtil;
import com.intellij.ide.util.PsiClassListCellRenderer;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.colors.EditorColors;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.markup.RangeHighlighter;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.Pass;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.JavaRecursiveElementWalkingVisitor;
import com.intellij.psi.JavaTokenType;
import com.intellij.psi.JspPsiUtil;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiAnonymousClass;
import com.intellij.psi.PsiAssignmentExpression;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassInitializer;
import com.intellij.psi.PsiCodeBlock;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiExpressionStatement;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaToken;
import com.intellij.psi.PsiLocalVariable;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiMember;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.PsiModifierList;
import com.intellij.psi.PsiPackage;
import com.intellij.psi.PsiParenthesizedExpression;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiReferenceExpression;
import com.intellij.psi.PsiStatement;
import com.intellij.psi.PsiType;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import com.intellij.psi.impl.GeneratedMarkerVisitor;
import com.intellij.psi.impl.source.codeStyle.CodeEditUtil;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiElementProcessor;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.util.PsiUtil;
import com.intellij.psi.util.PsiUtilBase;
import com.intellij.refactoring.IntroduceHandlerBase;
import com.intellij.refactoring.RefactoringBundle;
import com.intellij.refactoring.rename.RenameJavaVariableProcessor;
import com.intellij.refactoring.util.CommonRefactoringUtil;
import com.intellij.refactoring.util.EnumConstantsUtil;
import com.intellij.refactoring.util.occurrences.OccurrenceManager;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.VisibilityUtil;
import gw.internal.gosu.parser.statements.CatchClause;
import gw.plugin.ij.lang.psi.IGosuPsiElement;
import gw.plugin.ij.lang.psi.impl.statements.GosuFieldImpl;
import gw.plugin.ij.lang.psi.impl.statements.GosuForEachStatementImpl;
import gw.plugin.ij.lang.psi.impl.statements.GosuUsingStatementImpl;
import gw.plugin.ij.lang.psi.impl.statements.params.GosuParameterListImpl;
import gw.plugin.ij.lang.psi.util.GosuPsiParseUtil;
import gw.plugin.ij.refactor.GosuRefactoringUtil;
import gw.plugin.ij.refactor.introduceVariable.GosuIntroduceVariableBase;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class GosuBaseExpressionToFieldHandler extends IntroduceHandlerBase {
  private static final Logger LOG = Logger.getInstance("#com.intellij.refactoring.introduceField.BaseExpressionToFieldHandler");

  public enum InitializationPlace {
    IN_CURRENT_METHOD,
    IN_FIELD_DECLARATION,
    IN_CONSTRUCTOR,
    IN_SETUP_METHOD
  }

  private final boolean myIsConstant;
  private PsiClass myParentClass;

  protected GosuBaseExpressionToFieldHandler(boolean isConstant) {
    myIsConstant = isConstant;
  }

  protected boolean invokeImpl(final Project project, @NotNull final PsiExpression selectedExpr, final Editor editor) {
    final PsiElement element = getPhysicalElement(selectedExpr);

    final PsiFile file = element.getContainingFile();
    LOG.assertTrue(file != null, "expr.getContainingFile() == null");

    if (LOG.isDebugEnabled()) {
      LOG.debug("expression:" + selectedExpr);
    }

    final PsiType tempType = getTypeByExpression(selectedExpr);
    if (tempType == null) {
      String message = RefactoringBundle.getCannotRefactorMessage(RefactoringBundle.message("unknown.expression.type"));
      CommonRefactoringUtil.showErrorHint(project, editor, message, getRefactoringName(), getHelpID());
      return false;
    }

    if (PsiType.VOID.equals(tempType)) {
      String message = RefactoringBundle.getCannotRefactorMessage(RefactoringBundle.message("selected.expression.has.void.type"));
      CommonRefactoringUtil.showErrorHint(project, editor, message, getRefactoringName(), getHelpID());
      return false;
    }

    myParentClass = getParentClass(selectedExpr);
    final List<PsiClass> classes = new ArrayList<>();
    PsiClass aClass = myParentClass;
    while (aClass != null) {
      classes.add(aClass);
      final PsiField psiField = ConvertToFieldRunnable.checkForwardRefs(selectedExpr, aClass);
      if (psiField != null && psiField.getParent() == aClass) {
        break;
      }
      aClass = PsiTreeUtil.getParentOfType(aClass, PsiClass.class, true);
    }
    if (classes.size() == 1 || editor == null || ApplicationManager.getApplication().isUnitTestMode()) {
      return !convertExpressionToField(selectedExpr, editor, file, project, tempType);
    } else {
      PsiClass selection = null;
      for (PsiClass psiClass : classes) {
        if (!(psiClass instanceof PsiAnonymousClass)) {
          selection = psiClass;
          break;
        }
      }
      NavigationUtil.getPsiElementPopup(classes.toArray(new PsiClass[classes.size()]), new PsiClassListCellRenderer(),
              "Choose class to introduce " + (myIsConstant ? "constant" : "field"),
              new PsiElementProcessor<PsiClass>() {
                @Override
                public boolean execute(@NotNull PsiClass aClass) {
                  myParentClass = aClass;
                  convertExpressionToField(selectedExpr, editor, file, project, tempType);
                  return false;
                }
              }, selection).showInBestPositionFor(editor);
    }
    return true;
  }

  private boolean convertExpressionToField(PsiExpression selectedExpr,
                                           @Nullable Editor editor,
                                           PsiFile file,
                                           final Project project,
                                           PsiType tempType) {
    if (myParentClass == null) {
      if (JspPsiUtil.isInJspFile(file)) {
        CommonRefactoringUtil.showErrorHint(project, editor, RefactoringBundle.message("error.not.supported.for.jsp", getRefactoringName()),
                getRefactoringName(), getHelpID());
        return true;
      } else {
        LOG.assertTrue(false);
        return true;
      }
    }

    if (!validClass(myParentClass, editor)) {
      return true;
    }

    if (!CommonRefactoringUtil.checkReadOnlyStatus(project, file)) {
      return true;
    }

    final OccurrenceManager occurrenceManager = createOccurrenceManager(selectedExpr, myParentClass);
    final PsiExpression[] occurrences = occurrenceManager.getOccurrences();
    final PsiElement anchorStatementIfAll = occurrenceManager.getAnchorStatementForAll();

    List<RangeHighlighter> highlighters = null;
    if (editor != null) {
      highlighters = GosuRefactoringUtil.highlightAllOccurrences(project, occurrences, editor);
    }

    PsiElement tempAnchorElement = GosuRefactoringUtil.getParentExpressionAnchorElement(selectedExpr);
    if (!Comparing.strEqual(GosuIntroduceConstantHandler.REFACTORING_NAME, getRefactoringName()) &&
            GosuIntroduceVariableBase.checkAnchorBeforeThisOrSuper(project, editor, tempAnchorElement, getRefactoringName(), getHelpID())) {
      return true;
    }

    final Settings settings =
            showRefactoringDialog(project, editor, myParentClass, selectedExpr, tempType,
                    occurrences, tempAnchorElement, anchorStatementIfAll, false);

    if (settings == null) {
      return true;
    }

    if (settings.getForcedType() != null) {
      tempType = settings.getForcedType();
    }
    final PsiType type = tempType;

    if (editor != null) {
      HighlightManager highlightManager = HighlightManager.getInstance(project);
      for (RangeHighlighter highlighter : highlighters) {
        highlightManager.removeSegmentHighlighter(editor, highlighter);
      }
    }


    final Runnable runnable =
            new ConvertToFieldRunnable(settings.getSelectedExpr(), settings, type, settings.getOccurrences(), occurrenceManager,
                    anchorStatementIfAll, tempAnchorElement, editor,
                    myParentClass);

    new WriteCommandAction(project, getRefactoringName()) {
      @Override
      protected void run(Result result) throws Throwable {
        runnable.run();
      }
    }.execute();
    return false;
  }

  public static void setModifiers(PsiField field, Settings settings) {
    if (!settings.isIntroduceEnumConstant()) {
      if (settings.isDeclareStatic()) {
        PsiUtil.setModifierProperty(field, PsiModifier.STATIC, true);
      }
      if (settings.isDeclareFinal()) {
        PsiUtil.setModifierProperty(field, PsiModifier.FINAL, true);
      }
      if (settings.isAnnotateAsNonNls()) {
        PsiAnnotation annotation = JavaPsiFacade.getInstance(field.getProject()).getElementFactory()
                .createAnnotationFromText("@" + AnnotationUtil.NON_NLS, field);
        final PsiModifierList modifierList = field.getModifierList();
        LOG.assertTrue(modifierList != null);
        modifierList.addAfter(annotation, null);
      }
    }
    JavaCodeStyleManager.getInstance(field.getProject()).shortenClassReferences(field);
  }

  public static PsiElement getPhysicalElement(final PsiExpression selectedExpr) {
    PsiElement element = selectedExpr.getUserData(GosuElementToWorkOn.PARENT);
    if (element == null) {
      element = selectedExpr;
    }
    return element;
  }

  private static TextAttributes highlightAttributes() {
    return EditorColorsManager.getInstance().getGlobalScheme().getAttributes(
            EditorColors.SEARCH_RESULT_ATTRIBUTES
    );
  }

  protected abstract OccurrenceManager createOccurrenceManager(PsiExpression selectedExpr, PsiClass parentClass);

  protected final PsiClass getParentClass() {
    return myParentClass;
  }

  protected abstract boolean validClass(PsiClass parentClass, Editor editor);

  private static PsiElement getNormalizedAnchor(PsiElement anchorElement) {
    PsiElement child = anchorElement;
    while (child != null) {
      PsiElement prev = child.getPrevSibling();
      if (GosuRefactoringUtil.isExpressionAnchorElement(prev)) {
        break;
      }
      if (prev instanceof PsiJavaToken && ((PsiJavaToken) prev).getTokenType() == JavaTokenType.LBRACE) {
        break;
      }
      child = prev;
    }

    child = PsiTreeUtil.skipSiblingsForward(child, PsiWhiteSpace.class, PsiComment.class);
    PsiElement anchor;
    if (child != null) {
      anchor = child;
    } else {
      anchor = anchorElement;
    }
    return anchor;
  }

  protected abstract String getHelpID();

  protected abstract Settings showRefactoringDialog(Project project, Editor editor, PsiClass parentClass, PsiExpression expr,
                                                    PsiType type, PsiExpression[] occurrences, PsiElement anchorElement,
                                                    PsiElement anchorElementIfAll, boolean isLocalVariable);


  private static PsiType getTypeByExpression(PsiExpression expr) {
    return GosuRefactoringUtil.getTypeByExpressionWithExpectedType(expr);
  }

  public PsiClass getParentClass(@NotNull PsiExpression initializerExpression) {
    PsiElement element = initializerExpression.getUserData(GosuElementToWorkOn.PARENT);
    if (element == null) {
      element = initializerExpression.getParent();
    }
    PsiElement parent = element;
    while (parent != null) {
      if (parent instanceof PsiClass && !(parent instanceof PsiAnonymousClass && myIsConstant)) {
        return (PsiClass) parent;
      }
      parent = parent.getParent();
    }
    return null;
  }

  public static PsiMethod getEnclosingConstructor(PsiClass parentClass, PsiElement element) {
    if (element == null) {
      return null;
    }
    final PsiMethod[] constructors = parentClass.getConstructors();
    for (PsiMethod constructor : constructors) {
      if (PsiTreeUtil.isAncestor(constructor, element, false)) {
        if (PsiTreeUtil.getParentOfType(element, PsiClass.class) != parentClass) {
          return null;
        }
        return constructor;
      }
    }
    return null;
  }

  private static void addInitializationToSetUp(final PsiExpression initializer,
                                               final PsiField field,
                                               final PsiExpression[] occurrences,
                                               final boolean replaceAll,
                                               final PsiClass parentClass) throws IncorrectOperationException {
    final PsiMethod setupMethod = TestFrameworks.getInstance().findOrCreateSetUpMethod(parentClass);

    assert setupMethod != null;

    PsiElement anchor = null;
    if (PsiTreeUtil.isAncestor(setupMethod, initializer, true)) {
      anchor = replaceAll
              ? GosuRefactoringUtil.getAnchorElementForMultipleExpressions(occurrences, setupMethod)
              : PsiTreeUtil.getParentOfType(initializer, PsiStatement.class);
    }

    final PsiExpressionStatement expressionStatement =
            (PsiExpressionStatement) JavaPsiFacade.getInstance(parentClass.getProject()).getElementFactory()
                    .createStatementFromText(field.getName() + "= expr;", null);
    PsiAssignmentExpression expr = (PsiAssignmentExpression) expressionStatement.getExpression();
    final PsiExpression rExpression = expr.getRExpression();
    LOG.assertTrue(rExpression != null);
    rExpression.replace(initializer);

    final PsiCodeBlock body = setupMethod.getBody();
    assert body != null;
    body.addBefore(expressionStatement, anchor);
  }

  private static void addInitializationToConstructors(PsiExpression initializerExpression,
                                                      PsiField field,
                                                      PsiMethod enclosingConstructor, final PsiClass parentClass) {
    try {
      PsiClass aClass = field.getContainingClass();
      PsiMethod[] constructors = aClass.getConstructors();

      boolean added = false;
      for (PsiMethod constructor : constructors) {
        if (constructor == enclosingConstructor) {
          continue;
        }
        PsiCodeBlock body = constructor.getBody();
        if (body == null) {
          continue;
        }
        PsiStatement[] statements = body.getStatements();
        if (statements.length > 0) {
          PsiStatement first = statements[0];
          if (first instanceof PsiExpressionStatement) {
            PsiExpression expression = ((PsiExpressionStatement) first).getExpression();
            if (expression instanceof PsiMethodCallExpression) {
              @NonNls String text = ((PsiMethodCallExpression) expression).getMethodExpression().getText();
              if ("this".equals(text)) {
                continue;
              }
            }
          }
        }
        PsiStatement assignment = createAssignment(field, initializerExpression, body.getLastChild(), parentClass);
        assignment = (PsiStatement) body.add(assignment);
        ChangeContextUtil.decodeContextInfo(assignment, field.getContainingClass(),
                GosuRefactoringUtil.createThisExpression(field.getManager(), null));
        added = true;
      }
      if (!added && enclosingConstructor == null) {
        PsiMethod constructor = (PsiMethod) aClass.add(GosuPsiParseUtil.createConstructor(aClass.getName(), aClass.getManager(), null));
        final PsiCodeBlock body = constructor.getBody();
        PsiStatement assignment = createAssignment(field, initializerExpression, body.getLastChild(), parentClass);
        assignment = (PsiStatement) body.add(assignment);
        ChangeContextUtil.decodeContextInfo(assignment, field.getContainingClass(),
                GosuRefactoringUtil.createThisExpression(field.getManager(), null));
      }
    } catch (IncorrectOperationException e) {
      LOG.error(e);
    }
  }

  private static PsiField createField(String fieldName,
                                      PsiType type,
                                      PsiExpression initializerExpr,
                                      boolean includeInitializer, final PsiClass parentClass) {
    @NonNls StringBuilder pattern = new StringBuilder();
    pattern.append("var ");
    pattern.append(fieldName);
    if (initializerExpr == null) {
      includeInitializer = false;
    }

    if (includeInitializer) {
      pattern.append(" = ").append(initializerExpr.getText());
    } else {
      pattern.append(": ").append(type != null ? type.getPresentableText() : "Object");
    }

    final PsiElement psiElement = GosuPsiParseUtil.parseProgramm(pattern.toString(), PsiManager.getInstance(parentClass.getProject()), null);

    final GosuFieldImpl field = PsiTreeUtil.findChildOfType(psiElement, GosuFieldImpl.class);

    GeneratedMarkerVisitor.markGenerated(field);

    return field;
  }

  private static PsiStatement createAssignment(PsiField field,
                                               PsiExpression initializerExpr,
                                               PsiElement context,
                                               final PsiClass parentClass) {
    try {
      PsiManager psiManager = parentClass.getManager();
      final PsiReferenceExpression fieldReference = RenameJavaVariableProcessor.createMemberReference(field, context);
      PsiAssignmentExpression assignmentExpression = GosuPsiParseUtil.createAssignmentStatement(psiManager.getProject(), field.getName(), initializerExpr);

      assignmentExpression = (PsiAssignmentExpression) CodeStyleManager.getInstance(psiManager.getProject()).reformat(assignmentExpression);
      assignmentExpression.getLExpression().replace(fieldReference);

      return (PsiStatement) CodeStyleManager.getInstance(field.getProject()).reformat(assignmentExpression);

    } catch (IncorrectOperationException e) {
      LOG.error(e);
      return null;
    }
  }


  protected Pass<GosuElementToWorkOn> getElementProcessor(final Project project, final Editor editor) {
    return new Pass<GosuElementToWorkOn>() {
      @Override
      public void pass(final GosuElementToWorkOn elementToWorkOn) {
        if (!isValidContext(elementToWorkOn)) {
          String message = RefactoringBundle.getCannotRefactorMessage(RefactoringBundle.message("error.wrong.caret.position.local.or.expression.name"));
          CommonRefactoringUtil.showErrorHint(project, editor, message, GosuIntroduceFieldHandler.REFACTORING_NAME, getHelpID());
          return;
        }

        final boolean hasRunTemplate = LookupManager.getActiveLookup(editor) == null;
        if (elementToWorkOn.getExpression() == null) {
          final PsiLocalVariable localVariable = elementToWorkOn.getLocalVariable();
          final boolean result = invokeImpl(project, localVariable, editor) && hasRunTemplate;
          if (result) {
            editor.getSelectionModel().removeSelection();
          }
        } else {
          if (invokeImpl(project, elementToWorkOn.getExpression(), editor) && hasRunTemplate) {
            editor.getSelectionModel().removeSelection();
          }
        }
      }
    };
  }

  private boolean isValidContext(final GosuElementToWorkOn elementToWorkOn) {
    if (elementToWorkOn == null) {
      return false;
    }

    final PsiLocalVariable localVariable = elementToWorkOn.getLocalVariable();
    if (localVariable == null) {
      return true;
    }

    final PsiElement parent = localVariable.getParent();
    if (parent instanceof GosuForEachStatementImpl ||
            parent instanceof GosuUsingStatementImpl ||
            parent instanceof GosuParameterListImpl) {
      return false;
    }

    if (parent instanceof IGosuPsiElement) {
      IGosuPsiElement gosuElement = (IGosuPsiElement) parent;
      if (gosuElement.getParsedElement() instanceof CatchClause) {
        return false;
      }
    }
    return true;
  }

  protected abstract String getRefactoringName();

  public static class Settings {
    private final String myFieldName;
    private final PsiType myForcedType;

    private final boolean myReplaceAll;
    private final boolean myDeclareStatic;
    private final boolean myDeclareFinal;
    private final InitializationPlace myInitializerPlace;
    private final String myVisibility;
    private final boolean myDeleteLocalVariable;
    private final TargetDestination myTargetClass;
    private final boolean myAnnotateAsNonNls;
    private final boolean myIntroduceEnumConstant;
    private final PsiExpression mySelectedExpr;
    private PsiExpression[] myOccurrences;

    public PsiLocalVariable getLocalVariable() {
      return myLocalVariable;
    }

    public boolean isDeleteLocalVariable() {
      return myDeleteLocalVariable;
    }

    private final PsiLocalVariable myLocalVariable;

    public String getFieldName() {
      return myFieldName;
    }

    public boolean isDeclareStatic() {
      return myDeclareStatic;
    }

    public boolean isDeclareFinal() {
      return myDeclareFinal;
    }

    public InitializationPlace getInitializerPlace() {
      return myInitializerPlace;
    }

    public String getFieldVisibility() {
      return myVisibility;
    }

    @Nullable
    public PsiClass getDestinationClass() {
      return myTargetClass != null ? myTargetClass.getTargetClass() : null;
    }

    public PsiType getForcedType() {
      return myForcedType;
    }

    public boolean isReplaceAll() {
      return myReplaceAll;
    }

    public boolean isAnnotateAsNonNls() {
      return myAnnotateAsNonNls;
    }

    public boolean isIntroduceEnumConstant() {
      return myIntroduceEnumConstant;
    }

    public Settings(String fieldName,
                    PsiExpression selectedExpr,
                    PsiExpression[] occurrences,
                    boolean replaceAll,
                    boolean declareStatic, boolean declareFinal,
                    InitializationPlace initializerPlace, String visibility, PsiLocalVariable localVariableToRemove, PsiType forcedType,
                    boolean deleteLocalVariable,
                    TargetDestination targetDestination,
                    final boolean annotateAsNonNls,
                    final boolean introduceEnumConstant) {

      myFieldName = fieldName;
      myOccurrences = occurrences;
      mySelectedExpr = selectedExpr;
      myReplaceAll = replaceAll;
      myDeclareStatic = declareStatic;
      myDeclareFinal = declareFinal;
      myInitializerPlace = initializerPlace;
      myVisibility = visibility;
      myLocalVariable = localVariableToRemove;
      myDeleteLocalVariable = deleteLocalVariable;
      myForcedType = forcedType;
      myTargetClass = targetDestination;
      myAnnotateAsNonNls = annotateAsNonNls;
      myIntroduceEnumConstant = introduceEnumConstant;
    }

    public Settings(String fieldName,
                    PsiExpression selectedExpression,
                    PsiExpression[] occurrences,
                    boolean replaceAll,
                    boolean declareStatic, boolean declareFinal,
                    InitializationPlace initializerPlace, String visibility, PsiLocalVariable localVariableToRemove, PsiType forcedType,
                    boolean deleteLocalVariable,
                    PsiClass targetClass,
                    final boolean annotateAsNonNls,
                    final boolean introduceEnumConstant) {

      this(fieldName, selectedExpression, occurrences, replaceAll, declareStatic, declareFinal, initializerPlace, visibility, localVariableToRemove, forcedType, deleteLocalVariable, new TargetDestination(targetClass), annotateAsNonNls, introduceEnumConstant);
    }

    public PsiExpression getSelectedExpr() {
      return mySelectedExpr;
    }

    public PsiExpression[] getOccurrences() {
      return myOccurrences;
    }

    public void setOccurrences(PsiExpression[] occurrences) {
      myOccurrences = occurrences;
    }
  }

  public static class TargetDestination {
    private final String myQualifiedName;
    private final Project myProject;

    private PsiClass myParentClass;
    private PsiClass myTargetClass;

    public TargetDestination(String qualifiedName, PsiClass parentClass) {
      myQualifiedName = qualifiedName;
      myParentClass = parentClass;
      myProject = parentClass.getProject();
    }

    public TargetDestination(@NotNull PsiClass targetClass) {
      myTargetClass = targetClass;
      myQualifiedName = targetClass.getQualifiedName();
      myProject = targetClass.getProject();
    }

    @Nullable
    public PsiClass getTargetClass() {
      if (myTargetClass != null) {
        return myTargetClass;
      }
      final String packageName = StringUtil.getPackageName(myQualifiedName);
      final String shortName = StringUtil.getShortName(myQualifiedName);
      if (Comparing.strEqual(myParentClass.getQualifiedName(), packageName)) {
        myTargetClass = (PsiClass) myParentClass.add(JavaPsiFacade.getElementFactory(myProject).createClass(shortName));
        return myTargetClass;
      }
      PsiPackage psiPackage = JavaPsiFacade.getInstance(myProject).findPackage(packageName);
      final PsiDirectory psiDirectory;
      if (psiPackage != null) {
        final PsiDirectory[] directories = psiPackage.getDirectories(GlobalSearchScope.allScope(myProject));
        psiDirectory = directories.length > 1 ? DirectoryChooserUtil.chooseDirectory(directories, null, myProject, new HashMap<PsiDirectory, String>()) : directories[0];
      } else {
        psiDirectory = PackageUtil.findOrCreateDirectoryForPackage(myProject, packageName, myParentClass.getContainingFile().getContainingDirectory(), false);
      }
      myTargetClass = psiDirectory != null ? JavaDirectoryService.getInstance().createClass(psiDirectory, shortName) : null;
      return myTargetClass;
    }
  }

  public static class ConvertToFieldRunnable implements Runnable {
    private PsiExpression mySelectedExpr;
    private final Settings mySettings;
    private final PsiElement myAnchorElement;
    private final Project myProject;
    private final String myFieldName;
    private final PsiType myType;
    private final PsiExpression[] myOccurrences;
    private final boolean myReplaceAll;
    private final OccurrenceManager myOccurrenceManager;
    private final PsiElement myAnchorStatementIfAll;
    private final PsiElement myAnchorElementIfOne;
    private final Boolean myOutOfCodeBlockExtraction;
    private final PsiElement myElement;
    private boolean myDeleteSelf;
    private final Editor myEditor;
    private final PsiClass myParentClass;

    private PsiField myField;

    public ConvertToFieldRunnable(PsiExpression selectedExpr,
                                  Settings settings,
                                  PsiType type,
                                  PsiExpression[] occurrences,
                                  OccurrenceManager occurrenceManager,
                                  PsiElement anchorStatementIfAll,
                                  PsiElement anchorElementIfOne,
                                  Editor editor,
                                  PsiClass parentClass) {
      mySelectedExpr = selectedExpr;
      mySettings = settings;
      myAnchorElement = settings.isReplaceAll() ? anchorStatementIfAll : anchorElementIfOne;
      myProject = selectedExpr.getProject();
      myFieldName = settings.getFieldName();
      myType = type;
      myOccurrences = occurrences;
      myReplaceAll = settings.isReplaceAll();
      myOccurrenceManager = occurrenceManager;
      myAnchorStatementIfAll = anchorStatementIfAll;
      myAnchorElementIfOne = anchorElementIfOne;
      myOutOfCodeBlockExtraction = selectedExpr.getUserData(GosuElementToWorkOn.OUT_OF_CODE_BLOCK);
      myDeleteSelf = myOutOfCodeBlockExtraction != null;
      myElement = getPhysicalElement(selectedExpr);
      if (myElement.getParent() instanceof PsiExpressionStatement && getNormalizedAnchor(myAnchorElement).equals(myAnchorElement) && selectedExpr.isPhysical()) {
        PsiStatement statement = (PsiStatement) myElement.getParent();
        if (statement.getParent() instanceof PsiCodeBlock) {
          myDeleteSelf = true;
        }
      }

      myEditor = editor;
      myParentClass = parentClass;
    }

    public void run() {
      try {
        InitializationPlace initializerPlace = mySettings.getInitializerPlace();
        final PsiLocalVariable localVariable = mySettings.getLocalVariable();
        final boolean deleteLocalVariable = mySettings.isDeleteLocalVariable();
        @Nullable PsiExpression initializer = null;
        if (localVariable != null) {
          initializer = localVariable.getInitializer();
        } else if (!(GosuRefactoringUtil.isPsiReferenceExpression(mySelectedExpr) && ((PsiReference) mySelectedExpr).resolve() == null)) {
          initializer = mySelectedExpr;
        }

        initializer = GosuIntroduceVariableBase.replaceExplicitWithDiamondWhenApplicable(initializer, myType);

        final PsiMethod enclosingConstructor = getEnclosingConstructor(myParentClass, myAnchorElement);
        final PsiClass destClass = mySettings.getDestinationClass() == null ? myParentClass : mySettings.getDestinationClass();

        if (!CommonRefactoringUtil.checkReadOnlyStatus(myProject, destClass.getContainingFile())) {
          return;
        }

        if (initializer != null) {
          ChangeContextUtil.encodeContextInfo(initializer, true);
        }
        myField = mySettings.isIntroduceEnumConstant() ? EnumConstantsUtil.createEnumConstant(destClass, myFieldName, initializer) :
                createField(myFieldName, myType, initializer, initializerPlace == InitializationPlace.IN_FIELD_DECLARATION && initializer != null,
                        myParentClass);

        setModifiers(myField, mySettings);
        myField = appendField(initializer, initializerPlace, destClass, myParentClass, myAnchorElement, myField);
        if (!mySettings.isIntroduceEnumConstant()) {
          VisibilityUtil.fixVisibility(myOccurrences, myField, mySettings.getFieldVisibility());
        }
        PsiStatement assignStatement = null;
        PsiElement anchorElementHere = null;
        if (initializerPlace == InitializationPlace.IN_CURRENT_METHOD && initializer != null ||
                initializerPlace == InitializationPlace.IN_CONSTRUCTOR && enclosingConstructor != null && initializer != null) {
          if (myReplaceAll) {
            if (enclosingConstructor != null) {
              final PsiElement anchorInConstructor = GosuRefactoringUtil.getAnchorElementForMultipleExpressions(mySettings.myOccurrences,
                      enclosingConstructor);
              anchorElementHere = anchorInConstructor != null ? anchorInConstructor : myAnchorStatementIfAll;
            } else {
              anchorElementHere = myAnchorStatementIfAll;
            }
          } else {
            anchorElementHere = myAnchorElementIfOne;
          }
          assignStatement = createAssignment(myField, initializer, anchorElementHere, myParentClass);
          if (!GosuIntroduceVariableBase.isLoopOrIf(anchorElementHere.getParent())) {
            final PsiElement added = anchorElementHere.getParent().addBefore(assignStatement, getNormalizedAnchor(anchorElementHere));
            GeneratedMarkerVisitor.markGenerated(added);
          }
        }
        if (initializerPlace == InitializationPlace.IN_CONSTRUCTOR && initializer != null) {
          addInitializationToConstructors(initializer, myField, enclosingConstructor, myParentClass);
        }
        if (initializerPlace == InitializationPlace.IN_SETUP_METHOD && initializer != null) {
          addInitializationToSetUp(initializer, myField, myOccurrences, myReplaceAll, myParentClass);
        }
        if (mySelectedExpr.getParent() instanceof PsiParenthesizedExpression) {
          mySelectedExpr = (PsiExpression) mySelectedExpr.getParent();
        }
        if (myOutOfCodeBlockExtraction != null) {
          final int endOffset = mySelectedExpr.getUserData(GosuElementToWorkOn.TEXT_RANGE).getEndOffset();
          PsiElement endElement = myElement.getContainingFile().findElementAt(endOffset);
          while (true) {
            final PsiElement parent = endElement.getParent();
            if (parent instanceof PsiClass) {
              break;
            }
            endElement = parent;
          }
          myElement.getParent().deleteChildRange(myElement, PsiTreeUtil.skipSiblingsBackward(endElement, PsiWhiteSpace.class));
        } else if (myDeleteSelf) {
          myElement.getParent().delete();
        }

        if (myReplaceAll) {
          List<PsiElement> array = new ArrayList<>();
          for (PsiExpression occurrence : myOccurrences) {
            if (occurrence instanceof PsiExpression) {
              occurrence = GosuRefactoringUtil.outermostParenthesizedExpression(occurrence);
            }
            if (myDeleteSelf && occurrence.equals(mySelectedExpr)) {
              continue;
            }
            final PsiElement replaced = GosuRefactoringUtil.replaceOccurenceWithFieldRef(occurrence, myField, destClass);
            if (replaced != null) {
              array.add(replaced);
            }
          }

          if (myEditor != null) {
            if (!ApplicationManager.getApplication().isUnitTestMode()) {
              PsiElement[] exprsToHighlight = PsiUtilBase.toPsiElementArray(array);
              HighlightManager highlightManager = HighlightManager.getInstance(myProject);
              highlightManager.addOccurrenceHighlights(myEditor, exprsToHighlight, highlightAttributes(), true, null);
              WindowManager
                      .getInstance().getStatusBar(myProject).setInfo(RefactoringBundle.message("press.escape.to.remove.the.highlighting"));
            }
          }
        } else {
          if (!myDeleteSelf) {
            mySelectedExpr = GosuRefactoringUtil.outermostParenthesizedExpression(mySelectedExpr);
            GosuRefactoringUtil.replaceOccurenceWithFieldRef(mySelectedExpr, myField, destClass);
          }
        }

        if (anchorElementHere != null && GosuIntroduceVariableBase.isLoopOrIf(anchorElementHere.getParent())) {
          GosuIntroduceVariableBase.putStatementInLoopBody(assignStatement, anchorElementHere.getParent(), anchorElementHere);
        }


        if (localVariable != null) {
          if (deleteLocalVariable) {
            localVariable.normalizeDeclaration();
            localVariable.delete();
          }
        }

        if (initializer != null) {
          ChangeContextUtil.clearContextInfo(initializer);
        }
      } catch (IncorrectOperationException e) {
        LOG.error(e);
      }
    }

    static PsiField appendField(final PsiExpression initializer,
                                InitializationPlace initializerPlace, final PsiClass destClass,
                                final PsiClass parentClass,
                                final PsiElement anchorElement,
                                final PsiField psiField) {
      PsiElement finalAnchorElement = null;
      if (destClass == parentClass) {
        for (finalAnchorElement = anchorElement;
             finalAnchorElement != null && finalAnchorElement.getParent() != destClass;
             finalAnchorElement = finalAnchorElement.getParent()) {

        }
      }
      PsiMember anchorMember = finalAnchorElement instanceof PsiMember ? (PsiMember) finalAnchorElement : null;

      if ((anchorMember instanceof PsiField) &&
              anchorMember.hasModifierProperty(PsiModifier.STATIC) == psiField.hasModifierProperty(PsiModifier.STATIC)) {
        return (PsiField) destClass.addBefore(psiField, anchorMember);
      } else if (anchorMember instanceof PsiClassInitializer) {

        PsiField field = (PsiField) destClass.addBefore(psiField, anchorMember);
        destClass.addBefore(CodeEditUtil.createLineFeed(field.getManager()), anchorMember);
        return field;
      } else {
        final PsiField forwardReference = initializerPlace == InitializationPlace.IN_FIELD_DECLARATION
                ? checkForwardRefs(initializer, parentClass) : null;
        if (forwardReference != null) {
          return forwardReference.getParent() == destClass ?
                  (PsiField) destClass.addAfter(psiField, forwardReference) :
                  (PsiField) forwardReference.getParent().addAfter(psiField, forwardReference);
        } else {
          return (PsiField) destClass.add(psiField);
        }
      }
    }

    @Nullable
    private static PsiField checkForwardRefs(@Nullable PsiExpression initializer, final PsiClass parentClass) {
      if (initializer == null) {
        return null;
      }
      final PsiField[] refConstantFields = new PsiField[1];
      initializer.accept(new JavaRecursiveElementWalkingVisitor() {
        @Override
        public void visitReferenceExpression(PsiReferenceExpression expression) {
          super.visitReferenceExpression(expression);
          final PsiElement resolve = expression.resolve();
          if (resolve instanceof PsiField &&
                  ((PsiField) resolve).hasModifierProperty(PsiModifier.FINAL) &&
                  PsiTreeUtil.isAncestor(parentClass, resolve, false) && ((PsiField) resolve).hasInitializer()) {
            if (refConstantFields[0] == null || refConstantFields[0].getTextOffset() < resolve.getTextOffset()) {
              refConstantFields[0] = (PsiField) resolve;
            }
          }
        }
      });
      return refConstantFields[0];
    }

    public PsiField getField() {
      return myField;
    }
  }
}