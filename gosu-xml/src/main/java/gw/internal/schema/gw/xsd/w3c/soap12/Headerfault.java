package gw.internal.schema.gw.xsd.w3c.soap12;

/***************************************************************************/
/* THIS IS AUTOGENERATED CODE - DO NOT MODIFY OR YOUR CHANGES WILL BE LOST */
/* THIS CODE CAN BE REGENERATED USING 'xsd-codegen'                        */
/***************************************************************************/
public class Headerfault extends gw.xml.XmlElement implements gw.internal.xml.IXmlGeneratedClass {

  public static final javax.xml.namespace.QName $ATTRIBUTE_QNAME_EncodingStyle = new javax.xml.namespace.QName( "", "encodingStyle", "" );
  public static final javax.xml.namespace.QName $ATTRIBUTE_QNAME_Message = new javax.xml.namespace.QName( "", "message", "" );
  public static final javax.xml.namespace.QName $ATTRIBUTE_QNAME_Namespace = new javax.xml.namespace.QName( "", "namespace", "" );
  public static final javax.xml.namespace.QName $ATTRIBUTE_QNAME_Part = new javax.xml.namespace.QName( "", "part", "" );
  public static final javax.xml.namespace.QName $ATTRIBUTE_QNAME_Use = new javax.xml.namespace.QName( "", "use", "" );
  public static final javax.xml.namespace.QName $QNAME = new javax.xml.namespace.QName( "http://schemas.xmlsoap.org/wsdl/soap12/", "headerfault", "wsoap12" );
  public static final gw.util.concurrent.LockingLazyVar<gw.lang.reflect.IType> TYPE = new gw.util.concurrent.LockingLazyVar<gw.lang.reflect.IType>( gw.lang.reflect.TypeSystem.getGlobalLock() ) {
          @Override
          protected gw.lang.reflect.IType init() {
            return gw.lang.reflect.TypeSystem.getByFullName( "gw.xsd.w3c.soap12.Headerfault" );
          }
        };
  private static final gw.util.concurrent.LockingLazyVar<gw.lang.reflect.IType> TYPEINSTANCETYPE = new gw.util.concurrent.LockingLazyVar<gw.lang.reflect.IType>( gw.lang.reflect.TypeSystem.getGlobalLock() ) {
          @Override
          protected gw.lang.reflect.IType init() {
            return gw.lang.reflect.TypeSystem.getByFullName( "gw.xsd.w3c.soap12.types.complex.THeaderFault" );
          }
        };

  public Headerfault() {
    this( new gw.internal.schema.gw.xsd.w3c.soap12.types.complex.THeaderFault() );
  }

  public Headerfault( gw.internal.schema.gw.xsd.w3c.soap12.types.complex.THeaderFault typeInstance ) {
    super( $QNAME, TYPE.get(), TYPEINSTANCETYPE.get(), typeInstance );
  }

  protected Headerfault( javax.xml.namespace.QName qname, gw.lang.reflect.IType type, gw.lang.reflect.IType schemaDefinedTypeInstanceType, gw.internal.schema.gw.xsd.w3c.xmlschema.types.complex.AnyType typeInstance ) {
    super( qname, type, schemaDefinedTypeInstanceType, typeInstance );
  }


  public gw.internal.schema.gw.xsd.w3c.soap12.types.complex.THeaderFault getTypeInstance() {
    //noinspection RedundantCast
    return (gw.internal.schema.gw.xsd.w3c.soap12.types.complex.THeaderFault) super.getTypeInstance();
  }

  public void setTypeInstance( gw.internal.schema.gw.xsd.w3c.soap12.types.complex.THeaderFault param ) {
    super.setTypeInstance( param );
  }


  public java.net.URI EncodingStyle() {
    return (java.net.URI) TYPE.get().getTypeInfo().getProperty( "EncodingStyle" ).getAccessor().getValue( this );
  }

  public void setEncodingStyle$( java.net.URI param ) {
    TYPE.get().getTypeInfo().getProperty( "EncodingStyle" ).getAccessor().setValue( this, param );
  }


  public javax.xml.namespace.QName Message() {
    return (javax.xml.namespace.QName) TYPE.get().getTypeInfo().getProperty( "Message" ).getAccessor().getValue( this );
  }

  public void setMessage$( javax.xml.namespace.QName param ) {
    TYPE.get().getTypeInfo().getProperty( "Message" ).getAccessor().setValue( this, param );
  }


  public java.net.URI Namespace() {
    return (java.net.URI) TYPE.get().getTypeInfo().getProperty( "Namespace" ).getAccessor().getValue( this );
  }

  public void setNamespace$( java.net.URI param ) {
    TYPE.get().getTypeInfo().getProperty( "Namespace" ).getAccessor().setValue( this, param );
  }


  public java.lang.String Part() {
    return (java.lang.String) TYPE.get().getTypeInfo().getProperty( "Part" ).getAccessor().getValue( this );
  }

  public void setPart$( java.lang.String param ) {
    TYPE.get().getTypeInfo().getProperty( "Part" ).getAccessor().setValue( this, param );
  }


  public gw.internal.schema.gw.xsd.w3c.soap12.enums.UseChoice Use() {
    return (gw.internal.schema.gw.xsd.w3c.soap12.enums.UseChoice) TYPE.get().getTypeInfo().getProperty( "Use" ).getAccessor().getValue( this );
  }

  public void setUse$( gw.internal.schema.gw.xsd.w3c.soap12.enums.UseChoice param ) {
    TYPE.get().getTypeInfo().getProperty( "Use" ).getAccessor().setValue( this, param );
  }

  public static gw.internal.schema.gw.xsd.w3c.soap12.Headerfault parse( byte[] byteArray ) {
    //noinspection RedundantArrayCreation
    return (gw.internal.schema.gw.xsd.w3c.soap12.Headerfault) TYPE.get().getTypeInfo().getMethod( "parse", gw.lang.reflect.TypeSystem.get( byte[].class ) ).getCallHandler().handleCall( null, new java.lang.Object[] { byteArray } );
  }

  public static gw.internal.schema.gw.xsd.w3c.soap12.Headerfault parse( byte[] byteArray, gw.xml.XmlParseOptions options ) {
    //noinspection RedundantArrayCreation
    return (gw.internal.schema.gw.xsd.w3c.soap12.Headerfault) TYPE.get().getTypeInfo().getMethod( "parse", gw.lang.reflect.TypeSystem.get( byte[].class ), gw.lang.reflect.TypeSystem.get( gw.xml.XmlParseOptions.class ) ).getCallHandler().handleCall( null, new java.lang.Object[] { byteArray, options } );
  }

  public static gw.internal.schema.gw.xsd.w3c.soap12.Headerfault parse( java.io.File file ) {
    //noinspection RedundantArrayCreation
    return (gw.internal.schema.gw.xsd.w3c.soap12.Headerfault) TYPE.get().getTypeInfo().getMethod( "parse", gw.lang.reflect.TypeSystem.get( java.io.File.class ) ).getCallHandler().handleCall( null, new java.lang.Object[] { file } );
  }

  public static gw.internal.schema.gw.xsd.w3c.soap12.Headerfault parse( java.io.File file, gw.xml.XmlParseOptions options ) {
    //noinspection RedundantArrayCreation
    return (gw.internal.schema.gw.xsd.w3c.soap12.Headerfault) TYPE.get().getTypeInfo().getMethod( "parse", gw.lang.reflect.TypeSystem.get( java.io.File.class ), gw.lang.reflect.TypeSystem.get( gw.xml.XmlParseOptions.class ) ).getCallHandler().handleCall( null, new java.lang.Object[] { file, options } );
  }

  public static gw.internal.schema.gw.xsd.w3c.soap12.Headerfault parse( java.io.InputStream inputStream ) {
    //noinspection RedundantArrayCreation
    return (gw.internal.schema.gw.xsd.w3c.soap12.Headerfault) TYPE.get().getTypeInfo().getMethod( "parse", gw.lang.reflect.TypeSystem.get( java.io.InputStream.class ) ).getCallHandler().handleCall( null, new java.lang.Object[] { inputStream } );
  }

  public static gw.internal.schema.gw.xsd.w3c.soap12.Headerfault parse( java.io.InputStream inputStream, gw.xml.XmlParseOptions options ) {
    //noinspection RedundantArrayCreation
    return (gw.internal.schema.gw.xsd.w3c.soap12.Headerfault) TYPE.get().getTypeInfo().getMethod( "parse", gw.lang.reflect.TypeSystem.get( java.io.InputStream.class ), gw.lang.reflect.TypeSystem.get( gw.xml.XmlParseOptions.class ) ).getCallHandler().handleCall( null, new java.lang.Object[] { inputStream, options } );
  }

  public static gw.internal.schema.gw.xsd.w3c.soap12.Headerfault parse( java.io.Reader reader ) {
    //noinspection RedundantArrayCreation
    return (gw.internal.schema.gw.xsd.w3c.soap12.Headerfault) TYPE.get().getTypeInfo().getMethod( "parse", gw.lang.reflect.TypeSystem.get( java.io.Reader.class ) ).getCallHandler().handleCall( null, new java.lang.Object[] { reader } );
  }

  public static gw.internal.schema.gw.xsd.w3c.soap12.Headerfault parse( java.io.Reader reader, gw.xml.XmlParseOptions options ) {
    //noinspection RedundantArrayCreation
    return (gw.internal.schema.gw.xsd.w3c.soap12.Headerfault) TYPE.get().getTypeInfo().getMethod( "parse", gw.lang.reflect.TypeSystem.get( java.io.Reader.class ), gw.lang.reflect.TypeSystem.get( gw.xml.XmlParseOptions.class ) ).getCallHandler().handleCall( null, new java.lang.Object[] { reader, options } );
  }

  public static gw.internal.schema.gw.xsd.w3c.soap12.Headerfault parse( java.lang.String xmlString ) {
    //noinspection RedundantArrayCreation
    return (gw.internal.schema.gw.xsd.w3c.soap12.Headerfault) TYPE.get().getTypeInfo().getMethod( "parse", gw.lang.reflect.TypeSystem.get( java.lang.String.class ) ).getCallHandler().handleCall( null, new java.lang.Object[] { xmlString } );
  }

  public static gw.internal.schema.gw.xsd.w3c.soap12.Headerfault parse( java.lang.String xmlString, gw.xml.XmlParseOptions options ) {
    //noinspection RedundantArrayCreation
    return (gw.internal.schema.gw.xsd.w3c.soap12.Headerfault) TYPE.get().getTypeInfo().getMethod( "parse", gw.lang.reflect.TypeSystem.get( java.lang.String.class ), gw.lang.reflect.TypeSystem.get( gw.xml.XmlParseOptions.class ) ).getCallHandler().handleCall( null, new java.lang.Object[] { xmlString, options } );
  }

  public static gw.internal.schema.gw.xsd.w3c.soap12.Headerfault parse( java.net.URL url ) {
    //noinspection RedundantArrayCreation
    return (gw.internal.schema.gw.xsd.w3c.soap12.Headerfault) TYPE.get().getTypeInfo().getMethod( "parse", gw.lang.reflect.TypeSystem.get( java.net.URL.class ) ).getCallHandler().handleCall( null, new java.lang.Object[] { url } );
  }

  public static gw.internal.schema.gw.xsd.w3c.soap12.Headerfault parse( java.net.URL url, gw.xml.XmlParseOptions options ) {
    //noinspection RedundantArrayCreation
    return (gw.internal.schema.gw.xsd.w3c.soap12.Headerfault) TYPE.get().getTypeInfo().getMethod( "parse", gw.lang.reflect.TypeSystem.get( java.net.URL.class ), gw.lang.reflect.TypeSystem.get( gw.xml.XmlParseOptions.class ) ).getCallHandler().handleCall( null, new java.lang.Object[] { url, options } );
  }

  @SuppressWarnings( {"UnusedDeclaration"} )
  private static final long FINGERPRINT = 2359489450031053463L;

}
