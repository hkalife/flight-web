package br.hk.flightweb;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Aviao.class)
public abstract class Aviao_ {

	public static volatile SetAttribute<Aviao, Voo> voo;
	public static volatile SingularAttribute<Aviao, Long> id;
	public static volatile SingularAttribute<Aviao, String> fabricante;
	public static volatile SingularAttribute<Aviao, String> prefixo;

}

