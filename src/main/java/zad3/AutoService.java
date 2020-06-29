package zad3;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import java.util.Scanner;

public class AutoService {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Scanner scan2 = new Scanner(System.in);
		Integer optionMenu;
		Car car1 = null, car2 = null;

		Configuration configuration = new Configuration.Builder().uri("bolt://localhost:7687").credentials("neo4j", "123").build();
		SessionFactory sessionFactory = new SessionFactory(configuration, "zad3");

		Session session = sessionFactory.openSession();

		CarService carService = new CarService(session);

		System.out.print("\nWitaj w serwisie samochodowym. Wybierz jedną z opcji poniżej.\n");

		while(true) {

			System.out.println("--------------------------------------------------------------------");
			System.out.println("1 - Dodawanie samochodu.");
			System.out.println("2 - Aktualizacja samochodu.");
			System.out.println("3 - Usuwanie somochodów.");
			System.out.println("4 - Usuwanie wszystkiego (etykiety oraz relacji.");
			System.out.println("5 - Wyświetl samochod.");
			System.out.println("6 - WYJŚCIE.");

			System.out.print("Wybierz opcję: ");
			optionMenu = scan.nextInt();


			if (optionMenu == 1) {
				//Tworzenie auta z włascicielem
				//Pierwszy samochod
				car1 = new Car();
				car1.setName("BMW");
				OwnerAuto ownerAuto = new OwnerAuto("Sobol");
				Mechanic mechanic1 = new Mechanic("Artur Kiec");
				MechanicService mechanicService = new MechanicService(session);
				car1.addOwner(ownerAuto);
				mechanic1.addCar(car1);
				carService.createOrUpdate(car1);
				mechanicService.createOrUpdate(mechanic1);

				//Drugi samochod
				car2 = new Car("Opel Zafira");
				OwnerAuto owner = new OwnerAuto("Kowalski");
				Mechanic mechanic2 = new Mechanic("Robert Karasek");
				car2.addOwner(owner);
				mechanic2.addCar(car2);
				carService.createOrUpdate(car2);
				mechanicService.createOrUpdate(mechanic2);

				System.out.println("Pomyślnie dodano samochody wraz z mechnikami oraz wlascicielem");
			} else if (optionMenu == 2) {
				//Aktualizowanie danych
				int id;
				String oldName, newName;
				carService.showOnlyIdAndNameCar(carService);
				System.out.print("Podaj ID auta: ");
				id = scan.nextInt();
				System.out.print("Podaj starą nazwe auta: ");
				oldName = scan2.nextLine();
				System.out.print("Podaj nową nazwe auta: ");
				newName = scan2.nextLine();
				carService.updateData(session, oldName, newName);
				if(car1.getName().equals(oldName)) {
					car1.setName(newName);
				}
				else if(car2.getName().equals(oldName)) {
					car2.setName(newName);
				}

			} else if (optionMenu == 3) {
				// Usuwanie elementu
				carService.showCars(carService);
				System.out.print("Które auto chcesz usunac z bazy?\nPodaj id: ");
				optionMenu = scan.nextInt();
				carService.delete((long)optionMenu);
				System.out.println("Zrealizowano polecenie.");

			} else if (optionMenu == 4) {
				// Usuwanie wszystkiego
				session.purgeDatabase();
				System.out.println("Pomyślnie usunieto dane z bazy");
			}else if (optionMenu == 5) {
				// Wyświetlanie danych z bazy
				carService.showCars(carService);

			} else if (optionMenu == 6) {
				// WYJŚCIE
				break;
			}
		}
		sessionFactory.close();
	}
}
