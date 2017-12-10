package fr.alma2017.composantClass;

import java.util.ArrayList;
import java.util.List;

import fr.alma2017.api.composant.IComposant;
import fr.alma2017.api.composant.IInterfaceComposantFournie;
import fr.alma2017.api.composant.IInterfaceComposantRequise;

public abstract class AComposant implements IComposant {

	protected IInterfaceComposantRequise interfaceRequise;
	protected IInterfaceComposantFournie interfaceFournie;
	protected List<IComposant> composantsInternes;

	public AComposant() {
		super();
		this.composantsInternes = new ArrayList<IComposant>();
	}

	@Override
	public IInterfaceComposantFournie getInterfaceFournie() {
		return this.interfaceFournie;
	}

	@Override
	public IInterfaceComposantRequise getInterfaceRequise() {
		return this.interfaceRequise;
	}

	@Override
	public List<IComposant> getComposantsInternes() {
		return this.composantsInternes;
	}

}