package lab_hotel;

import java.util.ArrayList;

class Hotel
{
	private ArrayList<Pokoj> pokoje;
	private int tablica_z_liczba_pokoi_na_pietro[];
	
	public Hotel(int n_tablica_z_liczba_pokoi_na_pietro[])
	{
		this.tablica_z_liczba_pokoi_na_pietro = n_tablica_z_liczba_pokoi_na_pietro;
		this.pokoje = new ArrayList<Pokoj>();
		
		for (int i = 0; i < tablica_z_liczba_pokoi_na_pietro.length; i++)
			for (int j =0; j < tablica_z_liczba_pokoi_na_pietro[i]; j++)
				this.pokoje.add(new Pokoj(i,j));
	}
	
	public void wyswietl_informacje_o_pokojach()
	{
		System.out.println("Aktualny stan hotelu: \n");
		
		for (Pokoj pokoj : this.pokoje)
		{
			System.out.println("Pokoj numer: "+pokoj.zwroc_numer_na_drzwiach());
			System.out.println("Pietro: "+pokoj.zwroc_pietro());
			System.out.println("Zarezerwowany: "+pokoj.czy_jest_zarezerwowany());
			
			if (pokoj.czy_jest_zarezerwowany() == true)
				System.out.println("Nazwisko rezerwujacego: "+pokoj.zwroc_rezerwujacego().zwroc_nazwisko());
			
			System.out.println();
		}
	}
	
	public Pokoj znajdz_pierwszy_wolny_pokoj()
	{
		for (Pokoj pokoj : this.pokoje)
			if (pokoj.czy_jest_zarezerwowany() == false)
				return pokoj;
		
		// nie znaleziono wolnego pokoju - zwracamy null
		return null;
	}
	public Pokoj zwroc_pokoj(int numer_na_drzwiach)
	{
		// wydajniej byloby przechowywac pokoje w postaci HashMap, zamiast ArrayList
		// nie chce jednak Panstwu komplikowac przykladu - jezeli bedzie potrzeba,
		// moge powiedziec o tym troche wiecej na nastepnym laboratorium
		
		for (Pokoj pokoj : this.pokoje)
			if (pokoj.zwroc_numer_na_drzwiach() == numer_na_drzwiach)
				return pokoj;
		
		// nie znaleziono pokoju o podanym numerze - zwracamy null
		return null;
	}
	public Pokoj zwroc_pokoj(int pietro, int numer_na_pietrze)
	{
		for (Pokoj pokoj : this.pokoje)
			if (pokoj.zwroc_pietro() == pietro && pokoj.zwroc_numer_na_pietrze() == numer_na_pietrze)
				return pokoj;
		
		return null;
	}
	
	public String zarezerwuj_pokoj(Pokoj pokoj, Osoba osoba)
	{
		if (pokoj == null)
			return "Nie wskazano zadnego pokoju"; else
		if (osoba == null)
			return "Nie wskazano osoby do rezerwacji"; else
		if (this.pokoje.contains(pokoj) == false)
			return "Podany pokoj nie nalezy do tego hotelu!"; else
		if (pokoj.czy_jest_zarezerwowany() == true)
			return "Pokoj jest juz zarezerwowany przez kogos innego!";
		
		// jezeli program dojdzie tutaj, to znaczy ze zaden z powyzszych warunkow nie byl spelniony
		pokoj.przypisz_rezerwujacego(osoba);
		return "Udalo sie zarezerwowac wybrany pokoj";
	}
	
	public String zwolnij_pokoj(Pokoj pokoj)
	{
		if (pokoj == null)
			return "Nie wskazano zadnego pokoju"; else
		if (this.pokoje.contains(pokoj) == false)
			return "Podany pokoj nie nalezy do tego hotelu!";
		
		pokoj.przypisz_rezerwujacego(null);
		return "Pokoj zostal zwolniony";
	}
	
	public int zwroc_liczbe_pieter()
	{
		return this.tablica_z_liczba_pokoi_na_pietro.length;
	}
	public int zwroc_liczbe_pokoi_na_pietrze(int nr_pietra)
	{
		if (nr_pietra < this.zwroc_liczbe_pieter())
			return this.tablica_z_liczba_pokoi_na_pietro[nr_pietra]; else
			return 0;
	}
}
