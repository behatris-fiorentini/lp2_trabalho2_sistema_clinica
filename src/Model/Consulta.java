package Model;

public class Consulta {

	private int cod_consulta;
	private String paciente;
	private String Procedimento;
	private int dia;
	private int mes;
	private int ano;
	private String hora;
	private float valor;
	private String pagamento;
	private String statusPagamento;
	private String descricaoPagamento;
	private String statusConsulta;
	private float valorTotal;
	private String medico;
	String pesquisa;

	public int getCod_consulta() {
		return cod_consulta;
	}
	public void setCod_consulta(int cod_consulta) {
		this.cod_consulta = cod_consulta;
	}
	public String getPaciente() {
		return paciente;
	}
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	public String getProcedimento() {
		return Procedimento;
	}
	public void setProcedimento(String procedimento) {
		Procedimento = procedimento;
	}	
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public String getPagamento() {
		return pagamento;
	}
	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}
	public String getStatusPagamento() {
		return statusPagamento;
	}
	public void setStatusPagamento(String statusPagamento) {
		this.statusPagamento = statusPagamento;
	}
	public String getDescricaoPagamento() {
		return descricaoPagamento;
	}
	public void setDescricaoPagamento(String descricaoPagamento) {
		this.descricaoPagamento = descricaoPagamento;
	}
	public String getStatusConsulta() {
		return statusConsulta;
	}
	public void setStatusConsulta(String statusConsulta) {
		this.statusConsulta = statusConsulta;
	}
	public float getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}
	public String getMedico() {
		return medico;
	}
	public void setMedico(String medico) {
		this.medico = medico;
	}
	public String getPesquisa() {
		return pesquisa;
	}
	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

}
