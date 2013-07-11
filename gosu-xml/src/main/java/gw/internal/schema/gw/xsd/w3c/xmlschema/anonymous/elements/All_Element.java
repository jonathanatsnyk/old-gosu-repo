package gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements;

/***************************************************************************/
/* THIS IS AUTOGENERATED CODE - DO NOT MODIFY OR YOUR CHANGES WILL BE LOST */
/* THIS CODE CAN BE REGENERATED USING 'xsd-codegen'                        */
/***************************************************************************/
public class All_Element extends gw.xml.XmlElement implements gw.internal.xml.IXmlGeneratedClass {

  public static final javax.xml.namespace.QName $ATTRIBUTE_QNAME_Block = new javax.xml.namespace.QName( "", "block", "" );
  public static final javax.xml.namespace.QName $ATTRIBUTE_QNAME_Default = new javax.xml.namespace.QName( "", "default", "" );
  public static final javax.xml.namespace.QName $ATTRIBUTE_QNAME_Fixed = new javax.xml.namespace.QName( "", "fixed", "" );
  public static final javax.xml.namespace.QName $ATTRIBUTE_QNAME_Form = new javax.xml.namespace.QName( "", "form", "" );
  public static final javax.xml.namespace.QName $ATTRIBUTE_QNAME_Id = new javax.xml.namespace.QName( "", "id", "" );
  public static final javax.xml.namespace.QName $ATTRIBUTE_QNAME_MaxOccurs = new javax.xml.namespace.QName( "", "maxOccurs", "" );
  public static final javax.xml.namespace.QName $ATTRIBUTE_QNAME_MinOccurs = new javax.xml.namespace.QName( "", "minOccurs", "" );
  public static final javax.xml.namespace.QName $ATTRIBUTE_QNAME_Name = new javax.xml.namespace.QName( "", "name", "" );
  public static final javax.xml.namespace.QName $ATTRIBUTE_QNAME_Nillable = new javax.xml.namespace.QName( "", "nillable", "" );
  public static final javax.xml.namespace.QName $ATTRIBUTE_QNAME_Ref = new javax.xml.namespace.QName( "", "ref", "" );
  public static final javax.xml.namespace.QName $ATTRIBUTE_QNAME_Type = new javax.xml.namespace.QName( "", "type", "" );
  public static final javax.xml.namespace.QName $ELEMENT_QNAME_Annotation = new javax.xml.namespace.QName( "http://www.w3.org/2001/XMLSchema", "annotation", "xs" );
  public static final javax.xml.namespace.QName $ELEMENT_QNAME_ComplexType = new javax.xml.namespace.QName( "http://www.w3.org/2001/XMLSchema", "complexType", "xs" );
  public static final javax.xml.namespace.QName $ELEMENT_QNAME_Key = new javax.xml.namespace.QName( "http://www.w3.org/2001/XMLSchema", "key", "xs" );
  public static final javax.xml.namespace.QName $ELEMENT_QNAME_Keyref = new javax.xml.namespace.QName( "http://www.w3.org/2001/XMLSchema", "keyref", "xs" );
  public static final javax.xml.namespace.QName $ELEMENT_QNAME_SimpleType = new javax.xml.namespace.QName( "http://www.w3.org/2001/XMLSchema", "simpleType", "xs" );
  public static final javax.xml.namespace.QName $ELEMENT_QNAME_Unique = new javax.xml.namespace.QName( "http://www.w3.org/2001/XMLSchema", "unique", "xs" );
  public static final javax.xml.namespace.QName $QNAME = new javax.xml.namespace.QName( "http://www.w3.org/2001/XMLSchema", "element", "xs" );
  public static final gw.util.concurrent.LockingLazyVar<gw.lang.reflect.IType> TYPE = new gw.util.concurrent.LockingLazyVar<gw.lang.reflect.IType>( gw.lang.reflect.TypeSystem.getGlobalLock() ) {
          @Override
          protected gw.lang.reflect.IType init() {
            return gw.lang.reflect.TypeSystem.getByFullName( "gw.xsd.w3c.xmlschema.anonymous.elements.All_Element" );
          }
        };
  private static final gw.util.concurrent.LockingLazyVar<gw.lang.reflect.IType> TYPEINSTANCETYPE = new gw.util.concurrent.LockingLazyVar<gw.lang.reflect.IType>( gw.lang.reflect.TypeSystem.getGlobalLock() ) {
          @Override
          protected gw.lang.reflect.IType init() {
            return gw.lang.reflect.TypeSystem.getByFullName( "gw.xsd.w3c.xmlschema.types.complex.NarrowMaxMin" );
          }
        };

  public All_Element() {
    this( new gw.internal.schema.gw.xsd.w3c.xmlschema.types.complex.NarrowMaxMin() );
  }

  public All_Element( gw.internal.schema.gw.xsd.w3c.xmlschema.types.complex.NarrowMaxMin typeInstance ) {
    super( $QNAME, TYPE.get(), TYPEINSTANCETYPE.get(), typeInstance );
  }

  protected All_Element( javax.xml.namespace.QName qname, gw.lang.reflect.IType type, gw.lang.reflect.IType schemaDefinedTypeInstanceType, gw.internal.schema.gw.xsd.w3c.xmlschema.types.complex.AnyType typeInstance ) {
    super( qname, type, schemaDefinedTypeInstanceType, typeInstance );
  }


  public gw.internal.schema.gw.xsd.w3c.xmlschema.types.complex.NarrowMaxMin getTypeInstance() {
    //noinspection RedundantCast
    return (gw.internal.schema.gw.xsd.w3c.xmlschema.types.complex.NarrowMaxMin) super.getTypeInstance();
  }

  public void setTypeInstance( gw.internal.schema.gw.xsd.w3c.xmlschema.types.complex.NarrowMaxMin param ) {
    super.setTypeInstance( param );
  }


  public gw.internal.schema.gw.xsd.w3c.xmlschema.Annotation Annotation() {
    return (gw.internal.schema.gw.xsd.w3c.xmlschema.Annotation) TYPE.get().getTypeInfo().getProperty( "Annotation" ).getAccessor().getValue( this );
  }

  public void setAnnotation$( gw.internal.schema.gw.xsd.w3c.xmlschema.Annotation param ) {
    TYPE.get().getTypeInfo().getProperty( "Annotation" ).getAccessor().setValue( this, param );
  }


  public java.lang.String Block() {
    return (java.lang.String) TYPE.get().getTypeInfo().getProperty( "Block" ).getAccessor().getValue( this );
  }

  public void setBlock$( java.lang.String param ) {
    TYPE.get().getTypeInfo().getProperty( "Block" ).getAccessor().setValue( this, param );
  }


  public gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.NarrowMaxMin_ComplexType ComplexType() {
    return (gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.NarrowMaxMin_ComplexType) TYPE.get().getTypeInfo().getProperty( "ComplexType" ).getAccessor().getValue( this );
  }

  public void setComplexType$( gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.NarrowMaxMin_ComplexType param ) {
    TYPE.get().getTypeInfo().getProperty( "ComplexType" ).getAccessor().setValue( this, param );
  }


  public java.lang.String Default() {
    return (java.lang.String) TYPE.get().getTypeInfo().getProperty( "Default" ).getAccessor().getValue( this );
  }

  public void setDefault$( java.lang.String param ) {
    TYPE.get().getTypeInfo().getProperty( "Default" ).getAccessor().setValue( this, param );
  }


  public java.lang.String Fixed() {
    return (java.lang.String) TYPE.get().getTypeInfo().getProperty( "Fixed" ).getAccessor().getValue( this );
  }

  public void setFixed$( java.lang.String param ) {
    TYPE.get().getTypeInfo().getProperty( "Fixed" ).getAccessor().setValue( this, param );
  }


  public gw.internal.schema.gw.xsd.w3c.xmlschema.enums.FormChoice Form() {
    return (gw.internal.schema.gw.xsd.w3c.xmlschema.enums.FormChoice) TYPE.get().getTypeInfo().getProperty( "Form" ).getAccessor().getValue( this );
  }

  public void setForm$( gw.internal.schema.gw.xsd.w3c.xmlschema.enums.FormChoice param ) {
    TYPE.get().getTypeInfo().getProperty( "Form" ).getAccessor().setValue( this, param );
  }


  public java.lang.String Id() {
    return (java.lang.String) TYPE.get().getTypeInfo().getProperty( "Id" ).getAccessor().getValue( this );
  }

  public void setId$( java.lang.String param ) {
    TYPE.get().getTypeInfo().getProperty( "Id" ).getAccessor().setValue( this, param );
  }


  public java.util.List<gw.internal.schema.gw.xsd.w3c.xmlschema.Key> Key() {
    //noinspection unchecked
    return (java.util.List<gw.internal.schema.gw.xsd.w3c.xmlschema.Key>) TYPE.get().getTypeInfo().getProperty( "Key" ).getAccessor().getValue( this );
  }

  public void setKey$( java.util.List<gw.internal.schema.gw.xsd.w3c.xmlschema.Key> param ) {
    TYPE.get().getTypeInfo().getProperty( "Key" ).getAccessor().setValue( this, param );
  }


  public java.util.List<gw.internal.schema.gw.xsd.w3c.xmlschema.Keyref> Keyref() {
    //noinspection unchecked
    return (java.util.List<gw.internal.schema.gw.xsd.w3c.xmlschema.Keyref>) TYPE.get().getTypeInfo().getProperty( "Keyref" ).getAccessor().getValue( this );
  }

  public void setKeyref$( java.util.List<gw.internal.schema.gw.xsd.w3c.xmlschema.Keyref> param ) {
    TYPE.get().getTypeInfo().getProperty( "Keyref" ).getAccessor().setValue( this, param );
  }


  public gw.internal.schema.gw.xsd.w3c.xmlschema.enums.NarrowMaxMin_MaxOccurs MaxOccurs() {
    return (gw.internal.schema.gw.xsd.w3c.xmlschema.enums.NarrowMaxMin_MaxOccurs) TYPE.get().getTypeInfo().getProperty( "MaxOccurs" ).getAccessor().getValue( this );
  }

  public void setMaxOccurs$( gw.internal.schema.gw.xsd.w3c.xmlschema.enums.NarrowMaxMin_MaxOccurs param ) {
    TYPE.get().getTypeInfo().getProperty( "MaxOccurs" ).getAccessor().setValue( this, param );
  }


  public gw.internal.schema.gw.xsd.w3c.xmlschema.enums.NarrowMaxMin_MinOccurs MinOccurs() {
    return (gw.internal.schema.gw.xsd.w3c.xmlschema.enums.NarrowMaxMin_MinOccurs) TYPE.get().getTypeInfo().getProperty( "MinOccurs" ).getAccessor().getValue( this );
  }

  public void setMinOccurs$( gw.internal.schema.gw.xsd.w3c.xmlschema.enums.NarrowMaxMin_MinOccurs param ) {
    TYPE.get().getTypeInfo().getProperty( "MinOccurs" ).getAccessor().setValue( this, param );
  }


  public java.lang.String Name() {
    return (java.lang.String) TYPE.get().getTypeInfo().getProperty( "Name" ).getAccessor().getValue( this );
  }

  public void setName$( java.lang.String param ) {
    TYPE.get().getTypeInfo().getProperty( "Name" ).getAccessor().setValue( this, param );
  }


  public java.lang.Boolean Nillable() {
    return (java.lang.Boolean) TYPE.get().getTypeInfo().getProperty( "Nillable" ).getAccessor().getValue( this );
  }

  public void setNillable$( java.lang.Boolean param ) {
    TYPE.get().getTypeInfo().getProperty( "Nillable" ).getAccessor().setValue( this, param );
  }


  public javax.xml.namespace.QName Ref() {
    return (javax.xml.namespace.QName) TYPE.get().getTypeInfo().getProperty( "Ref" ).getAccessor().getValue( this );
  }

  public void setRef$( javax.xml.namespace.QName param ) {
    TYPE.get().getTypeInfo().getProperty( "Ref" ).getAccessor().setValue( this, param );
  }


  public gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.NarrowMaxMin_SimpleType SimpleType() {
    return (gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.NarrowMaxMin_SimpleType) TYPE.get().getTypeInfo().getProperty( "SimpleType" ).getAccessor().getValue( this );
  }

  public void setSimpleType$( gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.NarrowMaxMin_SimpleType param ) {
    TYPE.get().getTypeInfo().getProperty( "SimpleType" ).getAccessor().setValue( this, param );
  }


  public javax.xml.namespace.QName Type() {
    return (javax.xml.namespace.QName) TYPE.get().getTypeInfo().getProperty( "Type" ).getAccessor().getValue( this );
  }

  public void setType$( javax.xml.namespace.QName param ) {
    TYPE.get().getTypeInfo().getProperty( "Type" ).getAccessor().setValue( this, param );
  }


  public java.util.List<gw.internal.schema.gw.xsd.w3c.xmlschema.Unique> Unique() {
    //noinspection unchecked
    return (java.util.List<gw.internal.schema.gw.xsd.w3c.xmlschema.Unique>) TYPE.get().getTypeInfo().getProperty( "Unique" ).getAccessor().getValue( this );
  }

  public void setUnique$( java.util.List<gw.internal.schema.gw.xsd.w3c.xmlschema.Unique> param ) {
    TYPE.get().getTypeInfo().getProperty( "Unique" ).getAccessor().setValue( this, param );
  }

  public static gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.All_Element parse( byte[] byteArray ) {
    //noinspection RedundantArrayCreation
    return (gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.All_Element) TYPE.get().getTypeInfo().getMethod( "parse", gw.lang.reflect.TypeSystem.get( byte[].class ) ).getCallHandler().handleCall( null, new java.lang.Object[] { byteArray } );
  }

  public static gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.All_Element parse( byte[] byteArray, gw.xml.XmlParseOptions options ) {
    //noinspection RedundantArrayCreation
    return (gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.All_Element) TYPE.get().getTypeInfo().getMethod( "parse", gw.lang.reflect.TypeSystem.get( byte[].class ), gw.lang.reflect.TypeSystem.get( gw.xml.XmlParseOptions.class ) ).getCallHandler().handleCall( null, new java.lang.Object[] { byteArray, options } );
  }

  public static gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.All_Element parse( java.io.File file ) {
    //noinspection RedundantArrayCreation
    return (gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.All_Element) TYPE.get().getTypeInfo().getMethod( "parse", gw.lang.reflect.TypeSystem.get( java.io.File.class ) ).getCallHandler().handleCall( null, new java.lang.Object[] { file } );
  }

  public static gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.All_Element parse( java.io.File file, gw.xml.XmlParseOptions options ) {
    //noinspection RedundantArrayCreation
    return (gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.All_Element) TYPE.get().getTypeInfo().getMethod( "parse", gw.lang.reflect.TypeSystem.get( java.io.File.class ), gw.lang.reflect.TypeSystem.get( gw.xml.XmlParseOptions.class ) ).getCallHandler().handleCall( null, new java.lang.Object[] { file, options } );
  }

  public static gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.All_Element parse( java.io.InputStream inputStream ) {
    //noinspection RedundantArrayCreation
    return (gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.All_Element) TYPE.get().getTypeInfo().getMethod( "parse", gw.lang.reflect.TypeSystem.get( java.io.InputStream.class ) ).getCallHandler().handleCall( null, new java.lang.Object[] { inputStream } );
  }

  public static gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.All_Element parse( java.io.InputStream inputStream, gw.xml.XmlParseOptions options ) {
    //noinspection RedundantArrayCreation
    return (gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.All_Element) TYPE.get().getTypeInfo().getMethod( "parse", gw.lang.reflect.TypeSystem.get( java.io.InputStream.class ), gw.lang.reflect.TypeSystem.get( gw.xml.XmlParseOptions.class ) ).getCallHandler().handleCall( null, new java.lang.Object[] { inputStream, options } );
  }

  public static gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.All_Element parse( java.io.Reader reader ) {
    //noinspection RedundantArrayCreation
    return (gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.All_Element) TYPE.get().getTypeInfo().getMethod( "parse", gw.lang.reflect.TypeSystem.get( java.io.Reader.class ) ).getCallHandler().handleCall( null, new java.lang.Object[] { reader } );
  }

  public static gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.All_Element parse( java.io.Reader reader, gw.xml.XmlParseOptions options ) {
    //noinspection RedundantArrayCreation
    return (gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.All_Element) TYPE.get().getTypeInfo().getMethod( "parse", gw.lang.reflect.TypeSystem.get( java.io.Reader.class ), gw.lang.reflect.TypeSystem.get( gw.xml.XmlParseOptions.class ) ).getCallHandler().handleCall( null, new java.lang.Object[] { reader, options } );
  }

  public static gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.All_Element parse( java.lang.String xmlString ) {
    //noinspection RedundantArrayCreation
    return (gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.All_Element) TYPE.get().getTypeInfo().getMethod( "parse", gw.lang.reflect.TypeSystem.get( java.lang.String.class ) ).getCallHandler().handleCall( null, new java.lang.Object[] { xmlString } );
  }

  public static gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.All_Element parse( java.lang.String xmlString, gw.xml.XmlParseOptions options ) {
    //noinspection RedundantArrayCreation
    return (gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.All_Element) TYPE.get().getTypeInfo().getMethod( "parse", gw.lang.reflect.TypeSystem.get( java.lang.String.class ), gw.lang.reflect.TypeSystem.get( gw.xml.XmlParseOptions.class ) ).getCallHandler().handleCall( null, new java.lang.Object[] { xmlString, options } );
  }

  public static gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.All_Element parse( java.net.URL url ) {
    //noinspection RedundantArrayCreation
    return (gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.All_Element) TYPE.get().getTypeInfo().getMethod( "parse", gw.lang.reflect.TypeSystem.get( java.net.URL.class ) ).getCallHandler().handleCall( null, new java.lang.Object[] { url } );
  }

  public static gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.All_Element parse( java.net.URL url, gw.xml.XmlParseOptions options ) {
    //noinspection RedundantArrayCreation
    return (gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.All_Element) TYPE.get().getTypeInfo().getMethod( "parse", gw.lang.reflect.TypeSystem.get( java.net.URL.class ), gw.lang.reflect.TypeSystem.get( gw.xml.XmlParseOptions.class ) ).getCallHandler().handleCall( null, new java.lang.Object[] { url, options } );
  }

  @SuppressWarnings( {"UnusedDeclaration"} )
  private static final long FINGERPRINT = -3788403261967307401L;

}
