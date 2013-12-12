package estacionamento;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class RecebeMensagem extends CyclicBehaviour {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ACLMessage msg;
	
	public RecebeMensagem(ACLMessage mensagem) {
		super();
		this.msg = mensagem;
	}

	@Override
	public void action() {
		
		if (msg != null){
			System.out.println(msg.getSender() + " : " + msg.getContent());
		} else
			block();
	}
}
