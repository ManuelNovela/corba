
import InternetBakingApp.*;
///import InternetBakingApp.InternetBakingPackage.DivisionByZero;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

import java.util.Properties;

class InternetBakingImpl extends InternetBakingPOA {


    @Override
    public boolean autenticar (String usuario, String senha){
        return true;
    }

    @Override
    public double cosultarSaldo (String numeroConta){
        return 0.0;
    }

    @Override
    public boolean pagarServico (String entidade, String referencia){
        return true;
    }

    @Override
    public boolean transferencia (String numeroContaBenificiaria, double montante){
        return true;
    }

    private ORB orb;

    public void setORB(ORB orb_val) {
        orb = orb_val;
    }
}

public class corbaServer {

    public static void main(String args[]) {
        try {
            // create and initialize the ORB
            ORB orb = ORB.init(args, null);

            // get reference to rootpoa & activate the POAManager
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            // create servant and register it with the ORB
            InternetBakingImpl helloImpl = new InternetBakingImpl();
            helloImpl.setORB(orb);

            // get object reference from the servant
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloImpl);
            InternetBaking href = InternetBakingHelper.narrow(ref);

            // get the root naming context
            // NameService invokes the name service
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            // Use NamingContextExt which is part of the Interoperable
            // Naming Service (INS) specification.
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // bind the Object Reference in Naming
            String name = "InternetBaking";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, href);

            System.out.println("Ready..");

            // wait for invocations from clients
            orb.run();
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }

        System.out.println("Exiting ...");

    }
}