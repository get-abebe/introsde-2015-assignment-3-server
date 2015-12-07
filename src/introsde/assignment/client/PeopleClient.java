package introsde.assignment.client;

import introsde.assignment.model.Person;
import introsde.assignment.model.MeasureDefinition;
import introsde.assignment.model.LifeStatus;
import introsde.assignment.soap.People;
import introsde.assignment.model.HealthMeasureHistory;

import java.net.URL;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class PeopleClient{
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://192.168.56.1:6902/soap/people?wsdl");
        //https://fathomless-lowlands-3430.herokuapp.com/ws/people?wsdl
        //URL url = new URL("https://fathomless-lowlands-3430.herokuapp.com/ws/people?wsdl");
        //1st argument service URI, refer to wsdl assignment above
        //2nd argument is service name, refer to wsdl assignment above
        QName qname = new QName("http://soap.assignment.introsde/", "PeopleService");
        Service service = Service.create(url, qname);
      
        People people = service.getPort(People.class);
     
        // for person
     	JAXBContext jaxbContextPerson = JAXBContext.newInstance(Person.class);
     	Marshaller jaxbMarshallerPerson = jaxbContextPerson.createMarshaller();
     	jaxbMarshallerPerson.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
        // for LifeStatus
     	JAXBContext jaxbContextMeasure = JAXBContext.newInstance(LifeStatus.class);
     	Marshaller jaxbMarshallerMeasure = jaxbContextMeasure.createMarshaller();
     	jaxbMarshallerMeasure.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
        	
     
        System.out.println("********************** Method #1: readPersonList **********************");
        System.out.println("Parameters passed =======> null");
		List<Person> pList = people.readPersonList();
		System.out.println("Response =======>");
		personToXML(pList);
		System.out.println("\n");
						
		System.out.println("********************** Method #2: readPerson **********************");
		System.out.println("Parameters passed =======> personid: 52");
		Person p = people.readPerson(52);
		System.out.println("Response =======>");
		jaxbMarshallerPerson.marshal(p, System.out);
		System.out.println("\n");

		System.out.println("********************** Method #3: updatePerson **********************");
		System.out.println("Parameters passed =======> object: p");
		p.setName("abebe");
		p.setLastname("bob");
		p.setBirthdate("1988-10-12 23:00:00");
        p.setEmail("getachew@gmail.com");
        p.setUsername("john123");
		int updatedperson = people.updatePerson(p);
		Person up = people.readPerson(updatedperson);
		System.out.println("Response =======>");
		jaxbMarshallerPerson.marshal(up, System.out);
		System.out.println("\n");
		
		System.out.println("********************** Method #4: createPerson **********************");
		System.out.println("Parameters passed =======> object: newPerson");
		Person newperson = new Person();
		newperson.setName("fabio");
		newperson.setLastname("claudo");
		newperson.setEmail("tempEmail@tem.com");
		newperson.setBirthdate("1995-08-06");
		newperson.setUsername("peterk");
		int newPerson = people.createPerson(newperson);
		Person newCreatedPerson = people.readPerson(newPerson);
		System.out.println("Response =======>");
		System.out.println("The assigned id for the new created person is:  " + newPerson);
		jaxbMarshallerPerson.marshal(newCreatedPerson, System.out);
		System.out.println("\n");

		System.out.println("********************** Method #5: deletePerson **********************");
		System.out.println("Parameters passed =======> id: createdpersonid");
		int del = people.deletePerson(newCreatedPerson.getIdPerson());
		System.out.println("Response =======>");
		System.out.println("The id of the deleted person is " + del);
		System.out.println("\n");
		
		System.out.println("********************** Method #6: readPersonHistory **********************");
		System.out.println("Parameters passed =======> personid: 53 and measureType : weight");
		List<HealthMeasureHistory> mList = people.readPersonHistory(53, "weight");
		if (!mList.isEmpty())
		{
		System.out.println("Response =======>");
		measureToXML(mList);
		System.out.println("\n");
		}
		else
		{
			System.out.println("There are no person history by such id:" );
		    System.out.println("\n");
		}
			
		System.out.println("********************** Method #7: readMeasureType **********************");
		System.out.println("Parameters passed =======> null");
		List<MeasureDefinition> mDefs = people.readMeasureTypes();
		MeasureTypeXML list = new MeasureTypeXML();
		for (MeasureDefinition measureType : mDefs) {
			list.add(measureType.getMeasureName());
		}

		JAXBContext jaxbContext = JAXBContext.newInstance(MeasureTypeXML.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		System.out.println("Response =======>");
		jaxbMarshaller.marshal(list, System.out);
		System.out.println("\n");

		System.out.println("********************** Method #8: readPersonMeasure **********************");
		System.out.println("Parameters passed =======> personid: 52  measureType : weight and mid : 3");
		String lifestatuss = people.readPersonMeasure(52, "weight", 3);
		System.out.println("Response ==> " +"\n"+ "The value of the weight is: " +  lifestatuss.toString());
		System.out.println("\n");

		System.out.println("********************** Method #9: savePersonMeasure ********************** ");
		System.out.println("Parameters passed =======> personid: 101 and lifestatus ");
		int ids =101;
    	LifeStatus lifestatus1 = new LifeStatus();
    	MeasureDefinition meDef = new MeasureDefinition();
    	
    	meDef.setIdMeasureDef(1);
    	meDef.setMeasureName("weight");
    	lifestatus1.setMeasureDefinition(meDef);
    	lifestatus1.setValue("75");
    	System.out.println("Response =======>");
    	Person newlifestatus = people.savePersonMeasure(ids, lifestatus1);
    	jaxbMarshallerPerson.marshal(newlifestatus, System.out);
       	System.out.println("\n");

		
		System.out.println("********************** Method #10: updateMeasureDefinition **********************");
		System.out.println("Parameters passed =======> personid: 201 and lifestatus ");
		int id =201;
    	LifeStatus lifestatus = new LifeStatus();
    	MeasureDefinition meDefs = new MeasureDefinition();
    	
    	meDefs.setIdMeasureDef(1);
    	meDefs.setMeasureName("weight");
    	lifestatus.setMeasureDefinition(meDefs);
    	lifestatus.setValue("82");
    	System.out.println("Response =======>");
    	Person mListUpdated = people.updatePersonMeasure(id, lifestatus);
    	jaxbMarshallerPerson.marshal(mListUpdated, System.out);
    	System.out.println("\n");
    	
		
    }

    
    public static void personToXML(List<Person> people) {
		try {

			PersonList list = new PersonList();
			list.set(people);

			JAXBContext jaxbContext = JAXBContext.newInstance(PersonList.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);

			jaxbMarshaller.marshal(list, System.out);

		} catch (JAXBException e) {
			// some exception occured
			System.out.println(e);
		}
	}
    
    public static void measureToXML(List<HealthMeasureHistory> history) {
		try {

			MeasureList lists = new MeasureList();
			lists.set(history);

			JAXBContext jaxbContext = JAXBContext
					.newInstance(MeasureList.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);

			jaxbMarshaller.marshal(lists, System.out);

		} catch (JAXBException e) {
			// some exception occured
			System.out.println(e);
		}
	}
    
    @XmlRootElement(name = "people")
	static class PersonList {

		@XmlElement(name = "person")
		private List<Person> list;

		public PersonList() {
			list = new ArrayList<Person>();
		}

		public void add(Person p) {
			list.add(p);
		}

		public void set(List<Person> list) {
			this.list = list;
		}
	}
    
    @XmlRootElement(name = "healthProfile-history")
	static class MeasureList {

		@XmlElement(name = "measure")
		private List<HealthMeasureHistory> list;


		public void add(HealthMeasureHistory h) {
			list.add(h);
		}

		public void set(List<HealthMeasureHistory> list) {
			this.list = list;
		}
	}
    
    @XmlRootElement(name="measureTypes")
	static class MeasureTypeXML implements Serializable {
		private static final long serialVersionUID = 1L;

		@XmlElement(name = "measureType")
		List<String> measureType;
		
		public void add(String x){
			this.measureType.add(x);
		}
		
		public MeasureTypeXML(){
			measureType = new ArrayList<String>();
		}
		
		public List<String> get(){
			return measureType;
		}
		
		public void set(List<String> measureTypes){
			this.measureType = measureTypes;
		}
		
		
		
		
	}
    
   
}