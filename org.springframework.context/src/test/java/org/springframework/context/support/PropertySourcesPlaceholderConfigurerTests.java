/*
 * Copyright 2002-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.context.support;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mock.env.MockEnvironment;

import test.beans.TestBean;

/**
 * Unit tests for {@link PropertySourcesPlaceholderConfigurer}.
 * 
 * @author Chris Beams
 * @since 3.1
 */
public class PropertySourcesPlaceholderConfigurerTests {

	@Test
	public void spr7547_ppc() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.registerBeanDefinition("testBean",
				genericBeanDefinition(TestBean.class)
					.addPropertyValue("name", "${my.name}")
					.getBeanDefinition());

		PropertyPlaceholderConfigurer pc = new PropertyPlaceholderConfigurer();
		Resource resource = new ClassPathResource("PropertySourcesPlaceholderConfigurerTests.properties", this.getClass());
		pc.setLocation(resource);
		//pc.setProperties(new Properties() {{ setProperty("my.name", "bar"); }});
		pc.postProcessBeanFactory(bf);
	}

	@Test
	public void spr7547_pspc() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.registerBeanDefinition("testBean",
				genericBeanDefinition(TestBean.class)
					.addPropertyValue("name", "${my.name}")
					.getBeanDefinition());

		PropertySourcesPlaceholderConfigurer pc = new PropertySourcesPlaceholderConfigurer();
		pc.setEnvironment(new MockEnvironment());
		Resource resource = new ClassPathResource("PropertySourcesPlaceholderConfigurerTests.properties", this.getClass());
		pc.setLocation(resource);
		//pc.setProperties(new Properties() {{ setProperty("my.name", "bar"); }});
		pc.postProcessBeanFactory(bf);
	}

	@Ignore
	@Test(expected=IllegalArgumentException.class)
	public void environmentNotNull() {
		new PropertySourcesPlaceholderConfigurer().postProcessBeanFactory(new DefaultListableBeanFactory());
	}

	@Test
	public void localPropertiesOverrideFalse() {
		localPropertiesOverride(false);
	}

	@Test
	public void localPropertiesOverrideTrue() {
		localPropertiesOverride(true);
	}

	private void localPropertiesOverride(boolean override) {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.registerBeanDefinition("testBean",
				genericBeanDefinition(TestBean.class)
					.addPropertyValue("name", "${foo}")
					.getBeanDefinition());

		PropertySourcesPlaceholderConfigurer ppc = new PropertySourcesPlaceholderConfigurer();

		ppc.setLocalOverride(override);
		ppc.setProperties(MockEnvironment.withProperty("foo", "local").getPropertyResolver().asProperties());
		ppc.setEnvironment(MockEnvironment.withProperty("foo", "enclosing"));
		ppc.postProcessBeanFactory(bf);
		if (override) {
			assertThat(bf.getBean(TestBean.class).getName(), equalTo("local"));
		} else {
			assertThat(bf.getBean(TestBean.class).getName(), equalTo("enclosing"));
		}
	}

	@Test
	public void simpleReplacement() {
		DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
		bf.registerBeanDefinition("testBean",
				genericBeanDefinition(TestBean.class)
					.addPropertyValue("name", "${my.name}")
					.getBeanDefinition());

		MockEnvironment env = new MockEnvironment();
		env.setProperty("my.name", "myValue");

		PropertySourcesPlaceholderConfigurer ppc =
			new PropertySourcesPlaceholderConfigurer();
		ppc.setEnvironment(env);
		ppc.postProcessBeanFactory(bf);
		assertThat(bf.getBean(TestBean.class).getName(), equalTo("myValue"));
	}

}
