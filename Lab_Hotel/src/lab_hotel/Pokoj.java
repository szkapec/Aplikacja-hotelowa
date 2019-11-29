package lab_hotel;

public class Pokoj
{
	private int pietro;
	private int numer_na_pietrze;
	private int numer_na_drzwiach;
	private Osoba rezerwujacy;
	
	
	public Pokoj(int n_pietro, int n_numer_na_pietrze)
	{
		this.pietro = n_pietro;
		this.numer_na_pietrze = n_numer_na_pietrze;
		
		this.numer_na_drzwiach = pietro*100 +this.numer_na_pietrze +1;
		this.rezerwujacy = null;
	}
	
	public int zwroc_pietro()
	{
		return this.pietro;
	}
	public int zwroc_numer_na_pietrze()
	{
		return this.numer_na_pietrze;
	}

	public int zwroc_numer_na_drzwiach()
	{
		return this.numer_na_drzwiach;
	}
	public Osoba zwroc_rezerwujacego()
	{
		return this.rezerwujacy;
	}
	
	public void przypisz_rezerwujacego(Osoba n_osoba)
	{
		this.rezerwujacy = n_osoba;
	}
	
	public boolean czy_jest_zarezerwowany()
	{
		if (this.rezerwujacy == null)
			return false; else
			return true;
	}
}
