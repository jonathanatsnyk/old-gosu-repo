package gw.internal.schema.gw.xsd.w3c.soap12_envelope.types.complex;

/***************************************************************************/
/* THIS IS AUTOGENERATED CODE - DO NOT MODIFY OR YOUR CHANGES WILL BE LOST */
/* THIS CODE CAN BE REGENERATED USING 'xsd-codegen'                        */
/***************************************************************************/
public class Subcode extends gw.internal.schema.gw.xsd.w3c.xmlschema.types.complex.AnyType implements gw.internal.xml.IXmlGeneratedClass {

  public static final javax.xml.namespace.QName $ELEMENT_QNAME_Subcode = new javax.xml.namespace.QName( "http://www.w3.org/2003/05/soap-envelope", "Subcode", "soap12" );
  public static final javax.xml.namespace.QName $ELEMENT_QNAME_Value = new javax.xml.namespace.QName( "http://www.w3.org/2003/05/soap-envelope", "Value", "soap12" );
  public static final javax.xml.namespace.QName $QNAME = new javax.xml.namespace.QName( "http://www.w3.org/2003/05/soap-envelope", "subcode", "soap12" );
  public static final gw.util.concurrent.LockingLazyVar<gw.lang.reflect.IType> TYPE = new gw.util.concurrent.LockingLazyVar<gw.lang.reflect.IType>( gw.lang.reflect.TypeSystem.getGlobalLock() ) {
          @Override
          protected gw.lang.reflect.IType init() {
            return gw.lang.reflect.TypeSystem.getByFullName( "gw.xsd.w3c.soap12_envelope.types.complex.Subcode" );
          }
        };
  private static final gw.util.concurrent.LockingLazyVar<java.lang.Object> SCHEMAINFO = new gw.util.concurrent.LockingLazyVar<java.lang.Object>( gw.lang.reflect.TypeSystem.getGlobalLock() ) {
          @Override
          protected java.lang.Object init() {
            gw.lang.reflect.IType type = TYPE.get();
            return getSchemaInfoByType( type );
          }
        };

  public Subcode() {
    super( TYPE.get(), SCHEMAINFO.get() );
  }

  protected Subcode( gw.lang.reflect.IType type, java.lang.Object schemaInfo ) {
    super( type, schemaInfo );
  }


  @SuppressWarnings( {"MethodNameSameAsClassName"} )
  public gw.internal.schema.gw.xsd.w3c.soap12_envelope.anonymous.elements.Subcode_Subcode Subcode() {
    return (gw.internal.schema.gw.xsd.w3c.soap12_envelope.anonymous.elements.Subcode_Subcode) TYPE.get().getTypeInfo().getProperty( "Subcode" ).getAccessor().getValue( this );
  }

  public void setSubcode$( gw.internal.schema.gw.xsd.w3c.soap12_envelope.anonymous.elements.Subcode_Subcode param ) {
    TYPE.get().getTypeInfo().getProperty( "Subcode" ).getAccessor().setValue( this, param );
  }


  public javax.xml.namespace.QName Value() {
    return (javax.xml.namespace.QName) TYPE.get().getTypeInfo().getProperty( "Value" ).getAccessor().getValue( this );
  }

  public void setValue$( javax.xml.namespace.QName param ) {
    TYPE.get().getTypeInfo().getProperty( "Value" ).getAccessor().setValue( this, param );
  }


  public gw.internal.schema.gw.xsd.w3c.soap12_envelope.anonymous.elements.Subcode_Value Value_elem() {
    return (gw.internal.schema.gw.xsd.w3c.soap12_envelope.anonymous.elements.Subcode_Value) TYPE.get().getTypeInfo().getProperty( "Value_elem" ).getAccessor().getValue( this );
  }

  public void setValue_elem$( gw.internal.schema.gw.xsd.w3c.soap12_envelope.anonymous.elements.Subcode_Value param ) {
    TYPE.get().getTypeInfo().getProperty( "Value_elem" ).getAccessor().setValue( this, param );
  }

  @SuppressWarnings( {"UnusedDeclaration"} )
  private static final long FINGERPRINT = 6048722374270687003L;

}
