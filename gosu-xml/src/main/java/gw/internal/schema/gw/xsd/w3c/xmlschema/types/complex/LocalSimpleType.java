package gw.internal.schema.gw.xsd.w3c.xmlschema.types.complex;

/***************************************************************************/
/* THIS IS AUTOGENERATED CODE - DO NOT MODIFY OR YOUR CHANGES WILL BE LOST */
/* THIS CODE CAN BE REGENERATED USING 'xsd-codegen'                        */
/***************************************************************************/
public class LocalSimpleType extends gw.internal.schema.gw.xsd.w3c.xmlschema.types.complex.SimpleType implements gw.internal.xml.IXmlGeneratedClass {

  public static final javax.xml.namespace.QName $ATTRIBUTE_QNAME_Id = new javax.xml.namespace.QName( "", "id", "" );
  public static final javax.xml.namespace.QName $ELEMENT_QNAME_Annotation = new javax.xml.namespace.QName( "http://www.w3.org/2001/XMLSchema", "annotation", "xs" );
  public static final javax.xml.namespace.QName $ELEMENT_QNAME_List = new javax.xml.namespace.QName( "http://www.w3.org/2001/XMLSchema", "list", "xs" );
  public static final javax.xml.namespace.QName $ELEMENT_QNAME_Restriction = new javax.xml.namespace.QName( "http://www.w3.org/2001/XMLSchema", "restriction", "xs" );
  public static final javax.xml.namespace.QName $ELEMENT_QNAME_Union = new javax.xml.namespace.QName( "http://www.w3.org/2001/XMLSchema", "union", "xs" );
  public static final javax.xml.namespace.QName $QNAME = new javax.xml.namespace.QName( "http://www.w3.org/2001/XMLSchema", "localSimpleType", "xs" );
  public static final gw.util.concurrent.LockingLazyVar<gw.lang.reflect.IType> TYPE = new gw.util.concurrent.LockingLazyVar<gw.lang.reflect.IType>( gw.lang.reflect.TypeSystem.getGlobalLock() ) {
          @Override
          protected gw.lang.reflect.IType init() {
            return gw.lang.reflect.TypeSystem.getByFullName( "gw.xsd.w3c.xmlschema.types.complex.LocalSimpleType" );
          }
        };
  private static final gw.util.concurrent.LockingLazyVar<java.lang.Object> SCHEMAINFO = new gw.util.concurrent.LockingLazyVar<java.lang.Object>( gw.lang.reflect.TypeSystem.getGlobalLock() ) {
          @Override
          protected java.lang.Object init() {
            gw.lang.reflect.IType type = TYPE.get();
            return getSchemaInfoByType( type );
          }
        };

  public LocalSimpleType() {
    super( TYPE.get(), SCHEMAINFO.get() );
  }

  protected LocalSimpleType( gw.lang.reflect.IType type, java.lang.Object schemaInfo ) {
    super( type, schemaInfo );
  }


  public gw.internal.schema.gw.xsd.w3c.xmlschema.Annotation Annotation() {
    return (gw.internal.schema.gw.xsd.w3c.xmlschema.Annotation) TYPE.get().getTypeInfo().getProperty( "Annotation" ).getAccessor().getValue( this );
  }

  public void setAnnotation$( gw.internal.schema.gw.xsd.w3c.xmlschema.Annotation param ) {
    TYPE.get().getTypeInfo().getProperty( "Annotation" ).getAccessor().setValue( this, param );
  }


  public java.lang.String Id() {
    return (java.lang.String) TYPE.get().getTypeInfo().getProperty( "Id" ).getAccessor().getValue( this );
  }

  public void setId$( java.lang.String param ) {
    TYPE.get().getTypeInfo().getProperty( "Id" ).getAccessor().setValue( this, param );
  }


  public gw.internal.schema.gw.xsd.w3c.xmlschema.List List() {
    return (gw.internal.schema.gw.xsd.w3c.xmlschema.List) TYPE.get().getTypeInfo().getProperty( "List" ).getAccessor().getValue( this );
  }

  public void setList$( gw.internal.schema.gw.xsd.w3c.xmlschema.List param ) {
    TYPE.get().getTypeInfo().getProperty( "List" ).getAccessor().setValue( this, param );
  }


  public gw.internal.schema.gw.xsd.w3c.xmlschema.Restriction Restriction() {
    return (gw.internal.schema.gw.xsd.w3c.xmlschema.Restriction) TYPE.get().getTypeInfo().getProperty( "Restriction" ).getAccessor().getValue( this );
  }

  public void setRestriction$( gw.internal.schema.gw.xsd.w3c.xmlschema.Restriction param ) {
    TYPE.get().getTypeInfo().getProperty( "Restriction" ).getAccessor().setValue( this, param );
  }


  public gw.internal.schema.gw.xsd.w3c.xmlschema.Union Union() {
    return (gw.internal.schema.gw.xsd.w3c.xmlschema.Union) TYPE.get().getTypeInfo().getProperty( "Union" ).getAccessor().getValue( this );
  }

  public void setUnion$( gw.internal.schema.gw.xsd.w3c.xmlschema.Union param ) {
    TYPE.get().getTypeInfo().getProperty( "Union" ).getAccessor().setValue( this, param );
  }

  @SuppressWarnings( {"UnusedDeclaration"} )
  private static final long FINGERPRINT = -3788403261967307401L;

}
