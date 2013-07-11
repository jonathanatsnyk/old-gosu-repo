/*
 * Copyright 2013 Guidewire Software, Inc.
 */

package gw.plugin.ij.core;

import com.intellij.openapi.module.Module;
import gw.plugin.ij.util.IDEAUtil;
import org.jetbrains.annotations.NotNull;

public class IJModuleNode extends UnidirectionalCyclicGraph.Node<Module> {
  public IJModuleNode(@NotNull Module module) {
    super(module.getName(), module);
    for (Module dependency : IDEAUtil.getDependencies(module)) {
      addLink(dependency.getName());
    }
  }
}
