package agentes;

import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.Behaviour;

public class Aviao extends Agent{

	private String pistaLivre;
	
	protected void setup(){
		
		System.out.println("Agente aviao "+ getLocalName() + "pronto para aterrissar!");
		
		Object[] args = getArguments ();
		if(args != null && args.length > 0){
			pistaLivre = (String) args[0];
			System.out.println("Permissao para aterrisar na pista "+ pistaLivre);
			//dispara comportamento
			addBehaviour(new comportamentoAviao(this));
			//colocar mais comportamentos
		}
		else{
			System.out.println("Nao pretendo aterrissar !");
			doDelete();
		}
	}
	
	protected void takeDown(){
		System.out.println("Agente aviao " + getAID().getName() + "esta finalizado ");
	}
}
