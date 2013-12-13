package estacionamento;

import jade.core.Agent;
import jade.core.behaviours.*;
import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.*;
import jade.lang.acl.ACLMessage;

import estacionamento.Comportamentos;

public class Estacionamento extends Agent {

	protected void setup() {

		ServiceDescription servico = new ServiceDescription();
		servico.setName(this.getLocalName());
		servico.setType("disponibiliza vaga");
		registraServico(servico);
		RecebeMensagens("estacionamento", "Vou disponibilizar uma vaga! Pesquisando vaga!");

	}

	// Método para registrar o servico
	protected void registraServico(ServiceDescription sd) {
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		} catch (FIPAException e) {
			e.printStackTrace();
		}
	}

	// Método para adicionar um comportamento para receber mensagens
	protected void RecebeMensagens(final String mensagem, final String resp) {
		addBehaviour(new CyclicBehaviour() {

			@Override
			public void action() {
				ACLMessage msg = receive();
				if (msg != null) {
					ACLMessage reply = msg.createReply();
					reply.setContent(resp);
					myAgent.send(reply);
					buscaServico();
				} else
					block();
			}
		});
	}
	
	//Método para buscar servico de "vagas para carro"
	protected void buscaServico(){
		String argumento = "";

		// Captura argumentos
		Object[] args = getArguments();
		if (args != null && args.length > 0) {
			argumento = (String) args[0];
		}

		if (argumento.equalsIgnoreCase("vaga")) {
			ServiceDescription servico = new ServiceDescription();
			// O servico é estacionamento
			servico.setType("vaga para carro");
			busca(servico, "vaga");
		}

		addBehaviour(new CyclicBehaviour(this) {

			public void action() {
				ACLMessage msg = receive();
				if (msg != null) {
					System.out.println( getAgent().getAID().getName() + " diz que recebeu a mensagem: " + msg.getContent() + " de "+  msg.getSender());
				} else {
					System.out.println(getAgent().getAID().getName() + " diz: Nao encontrei vagas.");
					block();
				}
			}
		});
	}

	// Metodo que realiza a busca nas paginas amarelas da plataforma
		protected void busca(final ServiceDescription sd, final String Pedido) {
			// A cada minuto tenta buscar por agentes que fornecem
			// o servico
			addBehaviour(new TickerBehaviour(this, 60000) {

				protected void onTick() {
					DFAgentDescription dfd = new DFAgentDescription();
					dfd.addServices(sd);

					try {
						DFAgentDescription[] resultado = DFService.search(myAgent, dfd);
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
	
	
	protected void takeDown() {

	}
	

	private class VerificaVagasDisponiveis extends CyclicBehaviour {

		@Override
		public void action() {
			// TODO Auto-generated method stub

		}

	}

	private class DisponibilizaVagas extends CyclicBehaviour {

		@Override
		public void action() {
			// TODO Auto-generated method stub

		}

	}

	private class CobraPrecoEstacionammento extends OneShotBehaviour {

		@Override
		public void action() {
			// TODO Auto-generated method stub

		}

	}

	private class RecebePagamento extends OneShotBehaviour {

		@Override
		public void action() {
			// TODO Auto-generated method stub

		}

	}

}
