
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import InternetBakingApp.*;
//import InternetBakingApp.InternetBakingPackage.DivisionByZero;

import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import static java.lang.System.out;

public class corbaClient {

    static InternetBaking InternetBakingImpl;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String args[]) {

        try {
            // create and initialize the ORB
            ORB orb = ORB.init(args, null);

            // get the root naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            // Use NamingContextExt instead of NamingContext. This is
            // part of the Interoperable naming Service.
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // resolve the Object Reference in Naming
            String name = "InternetBaking";
            InternetBakingImpl = InternetBakingHelper.narrow(ncRef.resolve_str(name));

//			System.out.println(InternetBakingImpl);


            while (true) {
                out.println("1. Entrar");
                out.println("2. Consultar Saldo");
                out.println("3. Pagamento de Servico");
                out.println("4. Transferencia");
                out.println("5. exit");
                out.println("--");
                out.println("Escolha uma opcao: ");

                try {
                    String opt = br.readLine();
                    if (opt.equals("5")) {
                        break;
                    } else if (opt.equals("1")) {
                        out.println("usuario:");
                        String user = br.readLine();
                        out.println("senha");
                        String pass = br.readLine();

                        out.println("Login : " + status(InternetBakingImpl.autenticar(user, pass)));
                    
                    } else if (opt.equals("2")) {
                        out.println("numero da conta:");
                        String conta  = br.readLine();

                        out.println("Saldo actual : " + InternetBakingImpl.cosultarSaldo(conta)+ " Mzn");
                    } else if (opt.equals("3")) {
                        out.println("Entidade:");
                        String entidade  = br.readLine();
                        out.println("Referencia:");
                        String referencia  = br.readLine();
                       
                        out.println("Pagamento de Servico : " + status(InternetBakingImpl.pagarServico(entidade, referencia)));
                    } else if (opt.equals("4")) {
                        out.println("Conta Benificiaria:");
                        String conta  = br.readLine();
                        out.println("Montante:");
                        double montante  = Double.parseDouble(br.readLine());
                        out.println("Transferencia :" + status(InternetBakingImpl.transferencia(conta, montante)));
                    }
                } catch (Exception e) {
                    out.println("===");
                    out.println("Error with numbers");
                    out.println("===");
                }
                out.println("");

            }
            //InternetBakingImpl.shutdown();
        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }
    }

    static String status(boolean status){
        if(status){
            return "\n_______________________________________\n Com Sucesso, Obrigado! \n_______________________________________";
        }
        return "\n_______________________________________\n Sem Sucesso, Error na transacao. \n_______________________________________";
    }
    static float getFloat(String number) throws Exception {
        out.print(number + ": ");
        return Float.parseFloat(br.readLine());
    }
}