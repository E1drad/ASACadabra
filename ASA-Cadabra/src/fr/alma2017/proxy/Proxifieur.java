package fr.alma2017.proxy;

import java.lang.reflect.Proxy;
import java.util.Arrays;

import fr.alma2017.api.IObservable;
import fr.alma2017.api.client.IClient;
import fr.alma2017.api.clientServer.IClientServerConfiguration;
import fr.alma2017.api.server.IBaseDonnees;
import fr.alma2017.api.server.IConnectionManager;
import fr.alma2017.api.server.ISecurityManager;
import fr.alma2017.api.server.IServer;
import fr.alma2017.api.server.IServerConfiguration;
import fr.alma2017.exception.NotProxiedClassException;

public class Proxifieur {

	private static Class<?>[] concat(Class<?>[] interfaces, Class<?> class1) {
		Class<?>[] res;
		if(Arrays.asList(interfaces).contains(class1) == false ) {
			res = new Class<?>[interfaces.length + 1];
			for(int i = 0; i < res.length; ++i){
				if(i < interfaces.length){
					res[i] = interfaces[i];
				}else{
					res[i] = class1;
				}
			}
		}else {
			res = interfaces;
		}
		return res;
	}

	public static Object getProxyFor(Object target, Class<?> classType) throws NotProxiedClassException{
		if (classType.equals(IClient.class)) {
			return Proxy.newProxyInstance(target.getClass().getClassLoader(), 
					concat(target.getClass().getInterfaces(), IObservable.class),
					new ProxyClient(target));
		}
		else if (classType.equals(IServer.class)) {
			return Proxy.newProxyInstance(target.getClass().getClassLoader(), 
					concat(target.getClass().getInterfaces(), IObservable.class),
					new ProxyServer(target));
		}
		else if (classType.equals(IBaseDonnees.class)) {
			return Proxy.newProxyInstance(target.getClass().getClassLoader(), 
					concat(target.getClass().getInterfaces(), IObservable.class),
					new ProxyBDD(target));
		}
		else if (classType.equals(IConnectionManager.class)) {
			return Proxy.newProxyInstance(target.getClass().getClassLoader(), 
					concat(target.getClass().getInterfaces(), IObservable.class),
					new ProxyConnectionManager(target));
		}
		else if (classType.equals(ISecurityManager.class)) {
			return Proxy.newProxyInstance(target.getClass().getClassLoader(), 
					concat(target.getClass().getInterfaces(), IObservable.class),
					new ProxySecurityManager(target));
		}
		else if (classType.equals(IClientServerConfiguration.class)) {
			return Proxy.newProxyInstance(target.getClass().getClassLoader(), 
					concat(target.getClass().getInterfaces(), IObservable.class),
					new ProxyConfigurationClientServer(target));
		}
		else if (classType.equals(IServerConfiguration.class)) {
			return Proxy.newProxyInstance(target.getClass().getClassLoader(), 
					concat(target.getClass().getInterfaces(), IObservable.class),
					new ProxyConfigurationServer(target));
		}
		throw new NotProxiedClassException();
	}


}
