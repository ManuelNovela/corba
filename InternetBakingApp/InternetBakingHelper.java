package InternetBakingApp;


/**
* InternetBakingApp/InternetBakingHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from InternetBakingApp.idl
* Sunday, 11 September 2022 23:15:06 o'clock CEST
*/

abstract public class InternetBakingHelper
{
  private static String  _id = "IDL:InternetBakingApp/InternetBaking:1.0";

  public static void insert (org.omg.CORBA.Any a, InternetBakingApp.InternetBaking that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static InternetBakingApp.InternetBaking extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (InternetBakingApp.InternetBakingHelper.id (), "InternetBaking");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static InternetBakingApp.InternetBaking read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_InternetBakingStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, InternetBakingApp.InternetBaking value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static InternetBakingApp.InternetBaking narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof InternetBakingApp.InternetBaking)
      return (InternetBakingApp.InternetBaking)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      InternetBakingApp._InternetBakingStub stub = new InternetBakingApp._InternetBakingStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static InternetBakingApp.InternetBaking unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof InternetBakingApp.InternetBaking)
      return (InternetBakingApp.InternetBaking)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      InternetBakingApp._InternetBakingStub stub = new InternetBakingApp._InternetBakingStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}