package estacionamento;

import jade.core.Agent;

import estacionamento.Comportamentos;

import jade.core.behaviours.*;
import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.*;
import jade.lang.acl.ACLMessage;

public class Vaga extends Agent {

	protected void setup() {

		ServiceDescription servico = new ServiceDescription();
		servico.setName(this.getLocalName());
		servico.setType("vaga para carro");
		registraServico(servico);
		RecebeMensagens("vaga", "Sou uma vaga disponível!");

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
				} else
					block();
			}
		});
	}

	protected void takeDown() {

	}
}