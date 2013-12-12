package agentes;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

public class comportamentoAviao extends Behaviour{

	int i = 0;
public comportamentoAviao(Agent a){
	super(a);
}
	@Override
	public void action() {
		//fazer lógica para quando encontrar pista vazia, o aviao aterrissar
		System.out.println(myAgent.getLocalName());
		
		
	}

	@Override
	public boolean done() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
