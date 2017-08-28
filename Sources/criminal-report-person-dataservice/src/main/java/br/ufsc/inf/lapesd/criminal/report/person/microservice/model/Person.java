package br.ufsc.inf.lapesd.criminal.report.person.microservice.model;

public class Person {
	
//	@SerializedName("@type")
//    protected List<String> type = new ArrayList<>();
	
	//@SerializedName("@id")
	//private String id;
	
    private String rg;
    private String primeiroNome;
    private String nome;
    private String sexo;
    private String cutis;
    private String dataNascimento;
    private String naturalidade;
    private String nacionalidade;
    
    //private String estadoCivil;
    private String profissao;
    private String instrucao;
    //private String idade;
    //private String tipoEnvolvimento;
    //private String naturezasEnvolvidas;
    //private String nodoId = UUID.randomUUID().toString();
    

    //Just apply some values in english, but NOT the label of property!
    public Person applyValuesEN() {        
        //this.type.add("foaf:Person\", \"schema:Person");
    	getCutis();
    	getRg();
    	getSexo();
    	getPrimeiroNome();
    	getNaturalidade();
    	getDataNascimento();
    	return this;
    }
    
    
//    public List<String> getType() {
//        return type;
//    }
    
    

    public String getId() {
		return rg;
	}

	public String getPrimeiroNome() {
		if(this.nome != null)
			this.primeiroNome = nome.split("\\s+")[0];
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}

//	public void setId(String id) {
//		this.id = id;
//	}

	public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

//    public String getTipoEnvolvimento() {
//        return tipoEnvolvimento;
//    }
//
//    public void setTipoEnvolvimento(String tipoEnvolvimento) {
//        this.tipoEnvolvimento = tipoEnvolvimento;
//    }

    public String getRg() {
    	if(rg != null)
    		this.rg = rg.replaceAll("[^0-9]", "");
        return rg;
    }

    public void setRg(String rg) {
    	if(rg != null)
    		this.rg = rg.replaceAll("[^0-9]", "");
    }

    public String getNaturalidade() {
    	if(this.naturalidade != null && this.naturalidade.contains("S.PAULO"))
    		this.naturalidade = naturalidade.replace("S.PAULO", "SAO PAULO");
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getSexo() {
    	if(sexo != null) {
    		String sexoPT = sexo.toLowerCase();
    		if(sexoPT.contains("masc"))
    			this.sexo = "male";
    		else if(sexoPT.contains("fem"))
    			this.sexo = "female";
    	}
        return sexo;
    }

    public void setSexo(String sexo) {
    	if(sexo != null) {
    		sexo = sexo.toLowerCase();
    		if(sexo.contains("masc"))
    			this.sexo = "male";
    		else if(sexo.contains("fem"))
    			this.sexo = "female";
    		else
    			this.sexo = sexo;
    	}
    }

    public String getDataNascimento() {    	
    	if(dataNascimento != null && dataNascimento.length() == 10) {
	    	String dd = dataNascimento.substring(0, 2);
	    	String mm = dataNascimento.substring(3, 5);
	    	String yyyy = dataNascimento.substring(6, 10);
	    	dataNascimento = yyyy+"-"+mm+"-"+dd;
    	}    	
        return dataNascimento;
    }    

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

//    public String getEstadoCivil() {
//        return estadoCivil;
//    }
//
//    public void setEstadoCivil(String estadoCivil) {
//        this.estadoCivil = estadoCivil;
//    }

    public String getInstrucao() {
        return instrucao;
    }

    public void setInstrucao(String instrucao) {
        this.instrucao = instrucao;
    }

    public String getCutis() {
    	if(cutis != null) {
    		String cutisColor = cutis.toLowerCase();
    		if(cutisColor.contains("vermelho"))
    			this.cutis = "red";
    		else if(cutisColor.contains("parda"))
    			this.cutis = "brown";
    		else if(cutisColor.contains("preta"))
    			this.cutis = "black";
    		else if(cutisColor.contains("amarela"))
    			this.cutis = "yellow";
    		else if (cutisColor.contains("branca"))
    			this.cutis = "white";
    		else
    			this.cutis = "otherColor";
    	}
    	return this.cutis;
    }

    public void setCutis(String cutis) {
    	if(cutis != null) {
    		cutis = cutis.toLowerCase();
    		if(cutis.contains("vermelho"))
    			this.cutis = "red";
    		else if(cutis.contains("parda"))
    			this.cutis = "brown";
    		else if(cutis.contains("preta"))
    			this.cutis = "black";
    		else if(cutis.contains("amarela"))
    			this.cutis = "yellow";
    		else if (cutis.contains("branca"))
    			this.cutis = "white";
    		else
    			this.cutis = "otherColor";
    	}
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

//    public String getIdade() {
//        return idade;
//    }
//
//    public void setIdade(String idade) {
//        this.idade = idade;
//    }

//    public String getNaturezasEnvolvidas() {
//        return naturezasEnvolvidas;
//    }
//
//    public void setNaturezasEnvolvidas(String naturezasEnvolvidas) {
//        this.naturezasEnvolvidas = naturezasEnvolvidas;
//    }

	public String getLabel() {
		return nome;
	}

//	public String getNodoId() {
//		return nodoId;
//	}

	

}
