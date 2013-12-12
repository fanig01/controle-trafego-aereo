package agentes;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;


public class TorreControle extends Agent{

	//fazer lógica para torre de controle
	protected void setup(){
		//agente controlador
		addBehaviour(new ComportamentoTorre);
	}
}
