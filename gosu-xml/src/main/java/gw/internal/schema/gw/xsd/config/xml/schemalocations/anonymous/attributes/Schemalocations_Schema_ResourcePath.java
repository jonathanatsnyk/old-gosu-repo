package gw.internal.schema.gw.xsd.config.xml.schemalocations.anonymous.attributes;

/***************************************************************************/
/* THIS IS AUTOGENERATED CODE - DO NOT MODIFY OR YOUR CHANGES WILL BE LOST */
/* THIS CODE CAN BE REGENERATED USING 'xsd-codegen'                        */
/***************************************************************************/
public class Schemalocations_Schema_ResourcePath implements gw.internal.xml.IXmlGeneratedClass {

  public static final javax.xml.namespace.QName $QNAME = new javax.xml.namespace.QName( "", "resourcePath", "" );
  public static final gw.util.concurrent.LockingLazyVar<gw.lang.reflect.IType> TYPE = new gw.util.concurrent.LockingLazyVar<gw.lang.reflect.IType>( gw.lang.reflect.TypeSystem.getGlobalLock() ) {
          @Override
          protected gw.lang.reflect.IType init() {
            return gw.lang.reflect.TypeSystem.getByFullName( "gw.xsd.config.xml.schemalocations.anonymous.attributes.Schemalocations_Schema_ResourcePath" );
          }
        };

  private Schemalocations_Schema_ResourcePath() {
  }

  public static gw.xml.XmlSimpleValue createSimpleValue( java.lang.String value ) {
    //noinspection RedundantArrayCreation
    return (gw.xml.XmlSimpleValue) TYPE.get().getTypeInfo().getMethod( "createSimpleValue", gw.lang.reflect.TypeSystem.get( java.lang.String.class ) ).getCallHandler().handleCall( null, new java.lang.Object[] { value } );
  }

  public static void set( gw.internal.schema.gw.xsd.w3c.xmlschema.types.complex.AnyType anyType, java.lang.String value ) {
    //noinspection RedundantArrayCreation
    TYPE.get().getTypeInfo().getMethod( "set", gw.lang.reflect.TypeSystem.get( gw.internal.schema.gw.xsd.w3c.xmlschema.types.complex.AnyType.class ), gw.lang.reflect.TypeSystem.get( java.lang.String.class ) ).getCallHandler().handleCall( null, new java.lang.Object[] { anyType, value } );
  }

  public static void set( gw.xml.XmlElement element, java.lang.String value ) {
    //noinspection RedundantArrayCreation
    TYPE.get().getTypeInfo().getMethod( "set", gw.lang.reflect.TypeSystem.get( gw.xml.XmlElement.class ), gw.lang.reflect.TypeSystem.get( java.lang.String.class ) ).getCallHandler().handleCall( null, new java.lang.Object[] { element, value } );
  }

  @SuppressWarnings( {"UnusedDeclaration"} )
  private static final long FINGERPRINT = -4817729403594797358L;

}
