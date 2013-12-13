package estacionamento;

import jade.core.Agent;
import estacionamento.Comportamentos;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class Carro extends Agent {

	protected void setup() {
		String argumento = "";

		// Captura argumentos
		Object[] args = getArguments();
		if (args != null && args.length > 0) {
			argumento = (String) args[0];
		}

		if (argumento.equalsIgnoreCase("estacionamento")) {
			ServiceDescription servico = new ServiceDescription();
			// O servico é estacionamento
			servico.setType("disponibiliza vaga");
			busca(servico, "Quero estacionar!");
		}

		addBehaviour(new CyclicBehaviour(this) {

			public void action() {
				ACLMessage msg = receive();
				if (msg != null) {
					System.out.println( getAgent().getAID().getName() + " diz que recebeu a mensagem: " + msg.getContent() + " de "+  msg.getSender());

				} else
					block();
			}
		});

	}

	// Metodo que r e a l i z a a busca nas paginas amarelas da plataforma
	protected void busca(final ServiceDescription sd, final String Pedido) {
		// A cada minuto t ent a buscar por ag ent e s que fornecem
		// o s e r v i c o
		addBehaviour(new TickerBehaviour(this, 60000) {

			protected void onTick() {
				DFAgentDescription dfd = new DFAgentDescription();
				dfd.addServices(sd);

				try {
					DFAgentDescription[] resultado = DFService.search(myAgent,
							dfd);
					if (resultado.length != 0) {
						ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
						msg.addReceiver(resultado[0].getName());
						msg.setContent(Pedido);
						myAgent.send(msg);
						stop();// finaliza o comportamento
					}
				} catch (FIPAException e) {
					e.printStackTrace();

				}

			}
		});

	}

}
