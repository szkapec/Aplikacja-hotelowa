package lab_hotel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Aplikacja
{
	private Hotel hotel;
	
	private Font duza_czcionka;
	private Font srednia_czcionka;
	
	
	
	public static void main(String[] args)
	{
		Aplikacja aplikacja = new Aplikacja();
		
		//aplikacja.uruchom_wersje_konsolowa();
		aplikacja.uruchom_wersje_okienkowa();
	}

	
	
//	public void uruchom_wersje_konsolowa()
//	{
//		int ustawienie_pokoi[] = new int[]{4,6,2};
//		this.hotel = new Hotel(ustawienie_pokoi);
//		
//		Osoba klient = new Osoba("Henryk","Waza");
//		
//		Pokoj znaleziony_pokoj = hotel.znajdz_pierwszy_wolny_pokoj();
//		String wynik_rezerwacji = hotel.zarezerwuj_pokoj(znaleziony_pokoj,klient);
//		System.out.println(wynik_rezerwacji);
//		
//		Osoba drugi_klient = new Osoba("Jan","Nowak");
//		wynik_rezerwacji = hotel.zarezerwuj_pokoj(hotel.znajdz_pierwszy_wolny_pokoj(),drugi_klient);
//		System.out.println(wynik_rezerwacji);
//		
//		hotel.wyswietl_informacje_o_pokojach();
//	}
	
	public void uruchom_wersje_okienkowa()
	{
		int ustawienie_pokoi[] = new int[]{4,6,3,10};
		this.hotel = new Hotel(ustawienie_pokoi);
		
		Osoba klient = new Osoba("Henryk","Waza");
		Pokoj znaleziony_pokoj = hotel.znajdz_pierwszy_wolny_pokoj();
		String wynik_rezerwacji = hotel.zarezerwuj_pokoj(znaleziony_pokoj,klient);
		
		this.duza_czcionka = new Font(null,Font.BOLD,32);
		this.srednia_czcionka = new Font(null,Font.PLAIN,24);
		
		this.utworz_okno_glowne();
	}
	
	
	private JFrame utworz_okno_glowne()
	{		
		final JFrame okno_glowne = new JFrame("System rezerwacji");
		okno_glowne.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel_glowny = new JPanel();
		panel_glowny.setLayout(new BoxLayout(panel_glowny,BoxLayout.Y_AXIS));
		panel_glowny.setBorder(new EmptyBorder(10,10,10,10));
		
		JLabel etykieta = new JLabel("Wybierz akcję:",JLabel.CENTER);
	    etykieta.setAlignmentX(Component.CENTER_ALIGNMENT);
		etykieta.setFont(duza_czcionka);

		JButton przycisk_stan = new JButton("Sprawdź stan obiektu");
		przycisk_stan.setFont(srednia_czcionka);
		przycisk_stan.setAlignmentX(Component.CENTER_ALIGNMENT);
		przycisk_stan.setMaximumSize(new Dimension(400,50));
		przycisk_stan.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					utworz_okno_stanu_obiektu();
				}
			});
		
		
		JButton przycisk_znajdz_pierwszy_wolny = new JButton("Znajdź pierwszy wolny pokój");
		przycisk_znajdz_pierwszy_wolny.setFont(srednia_czcionka);
		przycisk_znajdz_pierwszy_wolny.setAlignmentX(Component.CENTER_ALIGNMENT);
		przycisk_znajdz_pierwszy_wolny.setMaximumSize(new Dimension(400,50));
		przycisk_znajdz_pierwszy_wolny.addActionListener(new ActionListener()
			{		
				@Override
				public void actionPerformed(ActionEvent e)
				{
					Pokoj znaleziony_pokoj = hotel.znajdz_pierwszy_wolny_pokoj();
					if (znaleziony_pokoj != null)
						utworz_okno_podgladu_pokoju(znaleziony_pokoj,false); else
						JOptionPane.showMessageDialog(okno_glowne,"Brak wolnych pokoi!","Informacja",
								JOptionPane.ERROR_MESSAGE);
				}
			});
		
		panel_glowny.add(etykieta);
		panel_glowny.add(Box.createVerticalStrut(40));
		panel_glowny.add(przycisk_stan);
		panel_glowny.add(Box.createVerticalStrut(10));
		panel_glowny.add(przycisk_znajdz_pierwszy_wolny);
		panel_glowny.add(Box.createVerticalStrut(40));
		
		okno_glowne.add(panel_glowny);
		
		//okno_glowne.setSize(new Dimension(500,500));
		okno_glowne.pack();
		okno_glowne.setLocationRelativeTo(null);
		okno_glowne.setVisible(true);
		
		return okno_glowne;
	}
	
	private JFrame utworz_okno_stanu_obiektu()
	{
		final JFrame okno = new JFrame("Okno stanu obiektu");
		okno.addWindowListener(new WindowAdapter()
		{
            @Override
            public void windowClosing(WindowEvent e)
            {
                e.getWindow().dispose();
            }
		});
		
		JPanel panel = new JPanel(new GridLayout(0,1,10,10));
		panel.setBorder(new EmptyBorder(15,15,15,15));
		okno.add(panel);
		
		JLabel etykieta = new JLabel("Wybierz piętro:",JLabel.CENTER);
		etykieta.setFont(this.duza_czcionka);
		panel.add(etykieta);
		
		for (int i =0; i < this.hotel.zwroc_liczbe_pieter(); i++)
		{
			final int nr_pietra = i;
			
			JButton przycisk_pietra = new JButton("Piętro "+nr_pietra);
			przycisk_pietra.setFont(this.srednia_czcionka);
			przycisk_pietra.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						okno.dispose();
						
						utworz_okno_podgladu_pietra(nr_pietra);
					}
				});
			
			panel.add(przycisk_pietra);
		}
		
		okno.setSize(400,400);
		//okno.pack();
		okno.setLocationRelativeTo(null);
		okno.setVisible(true);
		
		return okno;
	}
	
	private JFrame utworz_okno_podgladu_pietra(final int numer_pietra)
	{
		final JFrame okno = new JFrame("Podgląd " + numer_pietra + " piętra");
		okno.addWindowListener(new WindowAdapter()
		{
            @Override
            public void windowClosing(WindowEvent e)
            {
                e.getWindow().dispose();
                utworz_okno_stanu_obiektu();
            }
		});
		
		JPanel panel = new JPanel(new GridLayout(0,1,10,10));
		panel.setBorder(new EmptyBorder(15,15,15,15));
		okno.add(panel);
		
		JLabel etykieta = new JLabel("Wybierz pokój:",JLabel.CENTER);
		etykieta.setFont(this.duza_czcionka);
		panel.add(etykieta);
		
		for (int i =0; i < hotel.zwroc_liczbe_pokoi_na_pietrze(numer_pietra); i++)
		{
			final Pokoj pokoj = hotel.zwroc_pokoj(numer_pietra,i);
			if (pokoj.zwroc_pietro() != numer_pietra)
				continue;

			JButton przycisk_pietra = new JButton("Pokoj "+pokoj.zwroc_numer_na_drzwiach());
			przycisk_pietra.setFont(this.srednia_czcionka);
			
			if (pokoj.czy_jest_zarezerwowany() == true)
				przycisk_pietra.setBackground(new Color(255,200,15)); else
				przycisk_pietra.setBackground(new Color(180,230,30));
			
			przycisk_pietra.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e)
					{
						okno.dispose();
						
						utworz_okno_podgladu_pokoju(pokoj,true);
					}
				});
			
			panel.add(przycisk_pietra);
		}
		
		okno.pack();
		okno.setSize(okno.getWidth(),okno.getHeight()+50);
		okno.setLocationRelativeTo(null);
		okno.setVisible(true);	
		
		return okno;
	}
	
	private JFrame utworz_okno_podgladu_pokoju(final Pokoj pokoj, final boolean powrot_do_podgladu_pietra)
	{
		final JFrame okno = new JFrame("Podgląd pokoju nr "+pokoj.zwroc_numer_na_drzwiach());
		okno.addWindowListener(new WindowAdapter()
		{
            @Override
            public void windowClosing(WindowEvent e)
            {
                e.getWindow().dispose();
                
                if (powrot_do_podgladu_pietra == true)
                	utworz_okno_podgladu_pietra(pokoj.zwroc_pietro());
            }
		});
		
		JPanel panel = new JPanel(new GridLayout(0,2,10,10));
		panel.setBorder(new EmptyBorder(15,15,15,15));
		okno.add(panel);
		
		panel.add(this.utworz_srednia_etykiete("Numer pokoju:"));
		panel.add(this.utworz_srednia_etykiete(""+pokoj.zwroc_numer_na_drzwiach()));
		
		panel.add(this.utworz_srednia_etykiete("Piętro:"));
		if (pokoj.zwroc_pietro() != 0)
			panel.add(this.utworz_srednia_etykiete(""+pokoj.zwroc_pietro())); else
			panel.add(this.utworz_srednia_etykiete("parter"));
		
		panel.add(this.utworz_srednia_etykiete("Status: "));
		if (pokoj.czy_jest_zarezerwowany() == false)
			panel.add(this.utworz_srednia_etykiete("dostępny",Color.GREEN)); else
			panel.add(this.utworz_srednia_etykiete("zarezerwowany",Color.RED));	
		
		panel.add(this.utworz_srednia_etykiete("Osoba rezerwująca: "));
		if (pokoj.czy_jest_zarezerwowany() == true)
			panel.add(this.utworz_srednia_etykiete(pokoj.zwroc_rezerwujacego().zwroc_imie() + " " +
					pokoj.zwroc_rezerwujacego().zwroc_nazwisko())); else
			panel.add(this.utworz_srednia_etykiete("BRAK"));	
		
		panel.add(this.utworz_srednia_etykiete(""));
		panel.add(this.utworz_srednia_etykiete(""));
		
		if (pokoj.czy_jest_zarezerwowany() == true)
		{
			panel.add(this.utworz_srednia_etykiete(""));
			
			JButton przycisk_zwolnij = new JButton("Zwolnij pokoj");
			przycisk_zwolnij.setFont(this.srednia_czcionka);
			przycisk_zwolnij.addActionListener(new ActionListener()
				{			
					@Override
					public void actionPerformed(ActionEvent e)
					{
						hotel.zwolnij_pokoj(pokoj);
						
						JOptionPane.showMessageDialog(okno,"Zwolniono pokoj","Informacja",
								JOptionPane.INFORMATION_MESSAGE);
						okno.dispose();
						
						utworz_okno_podgladu_pokoju(pokoj,powrot_do_podgladu_pietra);
					}
				});
			
			panel.add(przycisk_zwolnij);
		} else
		{
			panel.add(this.utworz_srednia_etykiete("Imię rezerwującego:"));
			
			final JTextField pole_z_imieniem = this.utworz_srednie_pole_tekstowe();
			panel.add(pole_z_imieniem);
			
			panel.add(this.utworz_srednia_etykiete("Nazwisko rezerwującego:"));
			
			final JTextField pole_z_nazwiskiem = this.utworz_srednie_pole_tekstowe();
			panel.add(pole_z_nazwiskiem);
			
			panel.add(this.utworz_srednia_etykiete(""));
			JButton przycisk_zarezerwuj = new JButton("Zarezerwuj");
			przycisk_zarezerwuj.setFont(this.srednia_czcionka);
			przycisk_zarezerwuj.addActionListener(new ActionListener()
				{					
					@Override
					public void actionPerformed(ActionEvent e)
					{
						Osoba nowa_osoba = new Osoba(pole_z_imieniem.getText(),pole_z_nazwiskiem.getText());
						pokoj.przypisz_rezerwujacego(nowa_osoba);
						
						JOptionPane.showMessageDialog(okno,"Zarezerwowano pokoj!","Informacja",
								JOptionPane.INFORMATION_MESSAGE);
						okno.dispose();
						
						utworz_okno_podgladu_pokoju(pokoj,powrot_do_podgladu_pietra);
					}
				});
			
			panel.add(przycisk_zarezerwuj);
		}
		
		okno.pack();
		//okno.setSize(400,400);
		okno.setLocationRelativeTo(null);
		okno.setVisible(true);
		
		return okno;
	}
	
	private JLabel utworz_srednia_etykiete(String tekst, Color kolor_tekstu)
	{
		JLabel etykieta = new JLabel(tekst,JLabel.CENTER);
		etykieta.setFont(this.srednia_czcionka);
		etykieta.setForeground(kolor_tekstu);
		
		return etykieta;
	}
	private JLabel utworz_srednia_etykiete(String tekst)
	{
		return this.utworz_srednia_etykiete(tekst,Color.BLACK);
	}
	
	private JTextField utworz_srednie_pole_tekstowe()
	{
		JTextField pole = new JTextField();
		pole.setHorizontalAlignment(JTextField.CENTER);
		pole.setFont(this.srednia_czcionka);
		
		return pole;
	}
}
