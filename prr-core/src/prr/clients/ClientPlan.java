package prr.clients;

import prr.communications.Communication;
/* O clientType vai se atualizando sozinho por ser uma maquina de estatdo,
 * a ideia e ir fazendo intanceof no objeto na altura de calcular os precos.
 * Meter uma data de finals nos descendentes de clientPlan?
 */
public interface ClientPlan {
    public long calcTextPrice(Communication communication);

    public long calcCallPrice(Communication communication);

    public long calcVideoPrice(Communication communication);

    public long calcPrice(Communication communication);
}
