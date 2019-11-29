package lab_hotel;

public class Osoba
{
	private String imie;
	private String nazwisko;
	
	public Osoba(String n_imie, String n_nazwisko)
	{
		this.imie = n_imie;
		this.nazwisko = n_nazwisko;
	}
	
	public String zwroc_imie()
	{
		return this.imie;
	}
	public String zwroc_nazwisko()
	{
		return this.nazwisko;
	}
}
