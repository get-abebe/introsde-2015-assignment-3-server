package introsde.assignment.soap;
import introsde.assignment.model.LifeStatus;
import introsde.assignment.model.Person;
import introsde.assignment.model.MeasureDefinition;
import introsde.assignment.model.HealthMeasureHistory;

import java.util.List;
//import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebResult;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
public interface People {
	
	// Method #1
	@WebMethod(operationName="readPersonList")
    @WebResult(name="people") 
    public List<Person> readPersonList();
	
	// Method #2
    @WebMethod(operationName="readPerson")
    @WebResult(name="person") 
    public Person readPerson(@WebParam(name="personId") int id);
    
    // Method #3
    @WebMethod(operationName="updatePerson")
    @WebResult(name="person") 
    public int updatePerson(@WebParam(name="person") Person person);

    // Method #4
    @WebMethod(operationName="createPerson")
    @WebResult(name="personId") 
    public int createPerson(@WebParam(name="person") Person person);

    // Method #5
    @WebMethod(operationName="deletePerson")
    @WebResult(name="personId") 
    public int deletePerson(@WebParam(name="personId") int id);

    /*@WebMethod(operationName="updatePersonHealthProfile")
    @WebResult(name="hpId") 
    public int updatePersonHP(@WebParam(name="personId") int id, @WebParam(name="healthProfile") LifeStatus hp);
    */
    
    // Method #6
    @WebMethod(operationName="readPersonHistory")
    @WebResult(name="healthmeasurehistory")
    public List<HealthMeasureHistory> readPersonHistory(@WebParam(name="personId") int id, 
    		@WebParam(name="measureType") String measureType);
    
   
    // Method #7
    @WebMethod(operationName="readMeasureTypes")
    @WebResult(name="measureTypes")
    public List<MeasureDefinition> readMeasureTypes();
    
    // Method #8 
    @WebMethod(operationName="readPersonMeasure")
    @WebResult(name="meaureValue")
    public String readPersonMeasure(@WebParam(name="personId") int id, 
    		@WebParam(name="measureType") String measureType, 
    		@WebParam(name= "measureId") int mid );
    
    // Method #9 
    @WebMethod(operationName="savePersonMeasure")
    @WebResult(name="updateHistory")
    public Person savePersonMeasure(@WebParam(name="personId") int id, 
    		@WebParam(name="lifeStatus") LifeStatus lifeStatus );
    
        
    // Method #10 
    @WebMethod(operationName="updatePersonMeasure")
    @WebResult(name="updateMeasure")
    public Person updatePersonMeasure(@WebParam(name="personId") int id, 
    		@WebParam(name="lifeStatus") LifeStatus lifeStatus);
    
    
}