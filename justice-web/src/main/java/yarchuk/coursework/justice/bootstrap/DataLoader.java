package yarchuk.coursework.justice.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import yarchuk.coursework.justice.model.*;
import yarchuk.coursework.justice.service.*;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final ConsultationService consultationService;
    private final ClientService clientService;
    private final LawyerService lawyerService;
    private final IssueService issueService;
    private final IssueTypeService issueTypeService;
    private final SpecialityService specialityService;

    public DataLoader(ConsultationService consultationService, ClientService clientService, LawyerService lawyerService, IssueService issueService, IssueTypeService issueTypeService, SpecialityService specialityService) {
        this.consultationService = consultationService;
        this.clientService = clientService;
        this.lawyerService = lawyerService;
        this.issueService = issueService;
        this.issueTypeService = issueTypeService;

        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {
        int counter = issueTypeService.findAll().size();

        if (counter == 0) {
            loadData();
        }
    }

    private void loadData() {
        IssueType contract = new IssueType();
        contract.setName("Contracts");
        IssueType savedContract = issueTypeService.save(contract);

        IssueType theProperty = new IssueType();
        contract.setName("The property");
        IssueType savedTheProperty = issueTypeService.save(theProperty);

        IssueType roadAccident = new IssueType();
        roadAccident.setName("Road accident");
        IssueType savedRoadAccident = issueTypeService.save(roadAccident);

        IssueType another = new IssueType();
        another.setName("Another");
        IssueType savedAnother = issueTypeService.save(another);

        Speciality lawyer = new Speciality();
        lawyer.setDescription("Lawyer");
        Speciality savedLawyer = specialityService.save(lawyer);

        Speciality legalAdviser = new Speciality();
        legalAdviser.setDescription("Legal Adviser");
        Speciality savedLegalAdviser = specialityService.save(legalAdviser);

        Speciality notary = new Speciality();
        notary.setDescription("Notary");
        Speciality savedNotary = specialityService.save(notary);

        Client someOne = new Client();
        someOne.setFirstName("Aleksandr");
        someOne.setLastName("Feel");
        someOne.setCity("Odessa");
        someOne.setTelephone("380961691370");
        someOne.setTelephone("4FBB9Z4KJ1");

        Issue issue = new Issue();
        issue.setIssueType(savedAnother);
        issue.setClient(someOne);
        issue.setDescription("I've **************************************************************** *asd asd asd ********** asd asd sad asd ");

        someOne.getIssues().add(issue);
        clientService.save(someOne);

        Consultation someOneConsultation = new Consultation();
        someOneConsultation.setIssue(issue);
        someOneConsultation.setDate(LocalDate.now());
        someOneConsultation.setDescription("Someone promblem issue");

        consultationService.save(someOneConsultation);

        Client yarchukAleksandr = new Client();
        yarchukAleksandr.setFirstName("Aleksandr");
        yarchukAleksandr.setLastName("Yarchuk");
        yarchukAleksandr.setCity("Odessa");
        yarchukAleksandr.setTelephone("380961691369");
        yarchukAleksandr.setAuthCode("0FB29A4XJ1");

        Issue alexIssue = new Issue();
        alexIssue.setIssueType(savedContract);
        alexIssue.setClient(yarchukAleksandr);
        alexIssue.setDescription("I've got problems with job contract, they want to fire me because I violated NDA, which i didn't");

        yarchukAleksandr.getIssues().add(alexIssue);
        clientService.save(yarchukAleksandr);

        Consultation yarchukConsultation = new Consultation();
        yarchukConsultation.setIssue(alexIssue);
        yarchukConsultation.setDate(LocalDate.now());
        yarchukConsultation.setDescription("NDA issue");

        consultationService.save(yarchukConsultation);

        Lawyer lawyer1 = new Lawyer();
        lawyer1.setFirstName("Benjamin");
        lawyer1.setLastName("Crud");
        lawyer1.getSpecialities().add(savedLegalAdviser);

        lawyerService.save(lawyer1);

    }
}
