package introsde.assignment.soap;
import introsde.assignment.model.LifeStatus;
import introsde.assignment.model.Person;
import introsde.assignment.model.HealthMeasureHistory;
import introsde.assignment.model.MeasureDefinition;
import java.util.List;
import java.util.ArrayList;
import javax.jws.WebService;

//Service Implementation

@WebService(endpointInterface = "introsde.assignment.soap.People",
  serviceName="PeopleService")
public class PeopleImpl implements People {

    
  // Method #1:
  @Override
  public List<Person> readPersonList() {
	  System.out.println("Getting list of people...");
	        return Person.getAll();
  }

   // Method #2:
   @Override
   public Person readPerson(int id) {
       System.out.println("---> Reading Person by id = "+id);
       Person p = Person.getPersonById(id);
       if (p!=null) {
           System.out.println("---> Found Person by id = "+id+" => "+p.getName());
       } else {
           System.out.println("---> Didn't find any Person with  id = "+id);
       }
       return p;
   }
   
   // Method #3:
   @Override
   public int updatePerson(Person person) {
	
			Person p = Person.updatePerson(person);
			return p.getIdPerson();
		
   }
   
   // Method #4:
   @Override
   public int createPerson(Person person) {
       Person p = Person.savePerson(person);
       return p.getIdPerson();
   }
   
   
   // Method #5:
   @Override
   public int deletePerson(int id) {
       Person p = Person.getPersonById(id);
     if (p!=null) {
           Person.removePerson(p);
           return 0;
       } else {
           return -1;
       }
   }

   // Method #6:
   @Override
	public List<HealthMeasureHistory> readPersonHistory(int id, String measureType)
	{
		List<HealthMeasureHistory> hms = HealthMeasureHistory.getAll();
		List<HealthMeasureHistory> hmsr = new ArrayList<HealthMeasureHistory>();
		for (HealthMeasureHistory healthMeasureHistory : hms) {
			//System.out.println("===========================");
			if(healthMeasureHistory.getPerson().getIdPerson()==id
				&& healthMeasureHistory.getMeasureDefinition().getMeasureName()
				.equalsIgnoreCase(measureType))
				hmsr.add(healthMeasureHistory);
		}
		System.out.println(hmsr.size());
		return hmsr;
	}
   
   // Method #7:
  	@Override
  	public List<MeasureDefinition> readMeasureTypes() {
  		// TODO Auto-generated method stub
  		List<MeasureDefinition> m = MeasureDefinition.getAll();
  		return m;
  	}
  	
   // Method #8:
	@Override
	public String readPersonMeasure(int id, String measureType, int mid) {
		// TODO Auto-generated method stub
		List<HealthMeasureHistory> hms = HealthMeasureHistory.getAll();
		String returnValue = null;
		for (HealthMeasureHistory healthMeasureHistory : hms) {
			if(healthMeasureHistory.getPerson().getIdPerson()==id
				&& healthMeasureHistory.getMeasureDefinition().getMeasureName()
				.equalsIgnoreCase(measureType)
				&& healthMeasureHistory.getIdMeasureHistory() == mid)
			{
				returnValue = healthMeasureHistory.getValue();
			    break;
			}
			else
				returnValue = null;
				
		}
		return returnValue;
	}
   
	 // Method #9:
	@Override
	public Person savePersonMeasure(int id, LifeStatus lifeStatus) {
		
		// get people
		System.out.println(id);
		
		Person p = Person.getPersonById(id);
		List<LifeStatus> existingLs = p.getLifeStatus();
		LifeStatus newLiStatus = new LifeStatus();
		for (LifeStatus lStatus : existingLs) {
			if(lStatus.getMeasureDefinition().getIdMeasureDef()==
					lifeStatus.getMeasureDefinition().getIdMeasureDef())
			{
				newLiStatus=lStatus;
				String str = lifeStatus.getMeasureDefinition().getMeasureName();
				System.out.println(str);
			//	LifeStatus ls = LifeStatus.saveLifeStatus(lifeStatus);
			//	LifeStatus.removeLifeStatus(lStatus);
			    System.out.println("after");
			   
			}
		}
		// update lifestatus
		lifeStatus.setPerson(p);
		//return p;
		System.out.println("======================== "+ lifeStatus.getPerson().getIdPerson() +" ==================================");
		
		return p;
			
	}

	
	 // Method #10:
	@Override
	public Person updatePersonMeasure(int id,LifeStatus lifeStatus) {
		// get people
				System.out.println(id);
				Person p = Person.getPersonById(id);
			
				//get health measure history
				List<LifeStatus> liStatus = p.getLifeStatus();
				LifeStatus newLiStatus = new LifeStatus();
				for (LifeStatus lStatus : liStatus) {
					if(lStatus.getMeasureDefinition().getIdMeasureDef()==
							lifeStatus.getMeasureDefinition().getIdMeasureDef())
					{
						LifeStatus ls = LifeStatus.updateLifeStatus(lifeStatus);
						// LifeStatus.removeLifeStatus(lStatus);
						// ls.setValue(ls.getValue());
					}
				}
				
	    return p;
	}

}

