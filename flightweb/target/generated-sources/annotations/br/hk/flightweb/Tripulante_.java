package br.hk.flightweb;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tripulante.class)
public abstract class Tripulante_ {

	public static volatile SetAttribute<Tripulante, Voo> voo;
	public static volatile SingularAttribute<Tripulante, String> nome;
	public static volatile SingularAttribute<Tripulante, Long> id;
	public static volatile SingularAttribute<Tripulante, String> email;

}

