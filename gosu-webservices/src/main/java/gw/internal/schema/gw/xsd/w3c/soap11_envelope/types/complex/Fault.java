package gw.internal.schema.gw.xsd.w3c.soap11_envelope.types.complex;

/***************************************************************************/
/* THIS IS AUTOGENERATED CODE - DO NOT MODIFY OR YOUR CHANGES WILL BE LOST */
/* THIS CODE CAN BE REGENERATED USING 'xsd-codegen'                        */
/***************************************************************************/
public class Fault extends gw.internal.schema.gw.xsd.w3c.xmlschema.types.complex.AnyType implements gw.internal.xml.IXmlGeneratedClass {

  public static final javax.xml.namespace.QName $ELEMENT_QNAME_Detail = new javax.xml.namespace.QName( "", "detail", "" );
  public static final javax.xml.namespace.QName $ELEMENT_QNAME_Faultactor = new javax.xml.namespace.QName( "", "faultactor", "" );
  public static final javax.xml.namespace.QName $ELEMENT_QNAME_Faultcode = new javax.xml.namespace.QName( "", "faultcode", "" );
  public static final javax.xml.namespace.QName $ELEMENT_QNAME_Faultstring = new javax.xml.namespace.QName( "", "faultstring", "" );
  public static final javax.xml.namespace.QName $QNAME = new javax.xml.namespace.QName( "http://schemas.xmlsoap.org/soap/envelope/", "Fault", "tns" );
  public static final gw.util.concurrent.LockingLazyVar<gw.lang.reflect.IType> TYPE = new gw.util.concurrent.LockingLazyVar<gw.lang.reflect.IType>( gw.lang.reflect.TypeSystem.getGlobalLock() ) {
          @Override
          protected gw.lang.reflect.IType init() {
            return gw.lang.reflect.TypeSystem.getByFullName( "gw.xsd.w3c.soap11_envelope.types.complex.Fault" );
          }
        };
  private static final gw.util.concurrent.LockingLazyVar<java.lang.Object> SCHEMAINFO = new gw.util.concurrent.LockingLazyVar<java.lang.Object>( gw.lang.reflect.TypeSystem.getGlobalLock() ) {
          @Override
          protected java.lang.Object init() {
            gw.lang.reflect.IType type = TYPE.get();
            return getSchemaInfoByType( type );
          }
        };

  public Fault() {
    super( TYPE.get(), SCHEMAINFO.get() );
  }

  protected Fault( gw.lang.reflect.IType type, java.lang.Object schemaInfo ) {
    super( type, schemaInfo );
  }


  public gw.internal.schema.gw.xsd.w3c.soap11_envelope.anonymous.elements.Fault_Detail Detail() {
    return (gw.internal.schema.gw.xsd.w3c.soap11_envelope.anonymous.elements.Fault_Detail) TYPE.get().getTypeInfo().getProperty( "Detail" ).getAccessor().getValue( this );
  }

  public void setDetail$( gw.internal.schema.gw.xsd.w3c.soap11_envelope.anonymous.elements.Fault_Detail param ) {
    TYPE.get().getTypeInfo().getProperty( "Detail" ).getAccessor().setValue( this, param );
  }


  public java.net.URI Faultactor() {
    return (java.net.URI) TYPE.get().getTypeInfo().getProperty( "Faultactor" ).getAccessor().getValue( this );
  }

  public void setFaultactor$( java.net.URI param ) {
    TYPE.get().getTypeInfo().getProperty( "Faultactor" ).getAccessor().setValue( this, param );
  }


  public gw.internal.schema.gw.xsd.w3c.soap11_envelope.anonymous.elements.Fault_Faultactor Faultactor_elem() {
    return (gw.internal.schema.gw.xsd.w3c.soap11_envelope.anonymous.elements.Fault_Faultactor) TYPE.get().getTypeInfo().getProperty( "Faultactor_elem" ).getAccessor().getValue( this );
  }

  public void setFaultactor_elem$( gw.internal.schema.gw.xsd.w3c.soap11_envelope.anonymous.elements.Fault_Faultactor param ) {
    TYPE.get().getTypeInfo().getProperty( "Faultactor_elem" ).getAccessor().setValue( this, param );
  }


  public javax.xml.namespace.QName Faultcode() {
    return (javax.xml.namespace.QName) TYPE.get().getTypeInfo().getProperty( "Faultcode" ).getAccessor().getValue( this );
  }

  public void setFaultcode$( javax.xml.namespace.QName param ) {
    TYPE.get().getTypeInfo().getProperty( "Faultcode" ).getAccessor().setValue( this, param );
  }


  public gw.internal.schema.gw.xsd.w3c.soap11_envelope.anonymous.elements.Fault_Faultcode Faultcode_elem() {
    return (gw.internal.schema.gw.xsd.w3c.soap11_envelope.anonymous.elements.Fault_Faultcode) TYPE.get().getTypeInfo().getProperty( "Faultcode_elem" ).getAccessor().getValue( this );
  }

  public void setFaultcode_elem$( gw.internal.schema.gw.xsd.w3c.soap11_envelope.anonymous.elements.Fault_Faultcode param ) {
    TYPE.get().getTypeInfo().getProperty( "Faultcode_elem" ).getAccessor().setValue( this, param );
  }


  public java.lang.String Faultstring() {
    return (java.lang.String) TYPE.get().getTypeInfo().getProperty( "Faultstring" ).getAccessor().getValue( this );
  }

  public void setFaultstring$( java.lang.String param ) {
    TYPE.get().getTypeInfo().getProperty( "Faultstring" ).getAccessor().setValue( this, param );
  }


  public gw.internal.schema.gw.xsd.w3c.soap11_envelope.anonymous.elements.Fault_Faultstring Faultstring_elem() {
    return (gw.internal.schema.gw.xsd.w3c.soap11_envelope.anonymous.elements.Fault_Faultstring) TYPE.get().getTypeInfo().getProperty( "Faultstring_elem" ).getAccessor().getValue( this );
  }

  public void setFaultstring_elem$( gw.internal.schema.gw.xsd.w3c.soap11_envelope.anonymous.elements.Fault_Faultstring param ) {
    TYPE.get().getTypeInfo().getProperty( "Faultstring_elem" ).getAccessor().setValue( this, param );
  }

  @SuppressWarnings( {"UnusedDeclaration"} )
  private static final long FINGERPRINT = -6325559773743339107L;

}
