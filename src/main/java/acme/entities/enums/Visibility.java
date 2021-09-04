package acme.entities.enums;

public enum Visibility {
	PUBLIC("Public"), PRIVATE("Private");

	Visibility(final String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return this.name;
	}

}
