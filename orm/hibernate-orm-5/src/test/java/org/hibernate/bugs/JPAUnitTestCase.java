package org.hibernate.bugs;

import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.
	@Test
	public void hhh123Test() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Entity entity = new Entity();
		entity.setLabel("hibernate");
		entity.setUri(URI.create("https://hibernate.org/orm/"));
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
		entityManager.getTransaction().begin();
		final TypedQuery<Entity> eQuery = entityManager.createQuery("FROM Entity AS e",
			Entity.class);
		eQuery.getResultList().stream()
			.map(e -> e.getLabel() + ": " + e.getUri())
			.forEach(System.out::println);
		final TypedQuery<EntityDTO> dtoQuery = entityManager.createQuery(
			"SELECT new org.hibernate.bugs.EntityDTO(e.label, e.uri) FROM Entity AS e", EntityDTO.class);
		dtoQuery.getResultList()
			.forEach(System.out::println);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
