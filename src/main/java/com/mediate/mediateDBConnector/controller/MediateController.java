package com.mediate.mediateDBConnector.controller;

import com.mediate.mediateDBConnector.model.*;
import com.mediate.mediateDBConnector.repo.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Comparator;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

@RestController
public class MediateController {

    @Autowired
    private WebsiteRepo websiteRepo;

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private PersonCompanyRelationRepo personCompRelationRepo;

    @Autowired
    private CompanyOwnershipRepo companyOwnershipRepo;

    @CrossOrigin
    @RequestMapping(value = "/getWebsiteByUrl", method = RequestMethod.GET)
    public Website getWebsiteByUrl(@RequestParam("url") String url) {
        return websiteRepo.getWebsiteByUrl(url);
    }

    @CrossOrigin
    @RequestMapping(value = "/getAllWebsites", method = RequestMethod.GET)
    public List<Website> getAllWebsites() {
        return websiteRepo.findAll();
    }

    @CrossOrigin
    @RequestMapping(value = "/getAllUrls", method = RequestMethod.GET)
    public List<String> getAllUrls() {
        List<Website> sites = websiteRepo.findAll();
        List<String> urls = new ArrayList<String>();
        for(Iterator<Website> i = sites.iterator(); i.hasNext();){
            urls.add(i.next().getUrl());
        }
        return urls;
    }

    @CrossOrigin
    @RequestMapping(value = "/getAllCompanies", method = RequestMethod.GET)
    public List<Company> getAllCompanies(){
        return companyRepo.findAll();
    }

    @CrossOrigin
    @RequestMapping(value = "/getAllPeople", method = RequestMethod.GET)
    public List<Person> getAllPeople(){
        return personRepo.findAll();
    }

    @CrossOrigin
    @RequestMapping(value = "/getCompanyByUrl", method = RequestMethod.GET)
    public Company getCompanyByUrl(@RequestParam("url") String url){
        Website tempSite = websiteRepo.getWebsiteByUrl(url);
        return companyRepo.getCompanyById(tempSite.getCompanyId());
    }

    @CrossOrigin
    @RequestMapping(value = "/getCompanyById", method = RequestMethod.GET)
    public Company getCompanyByUrl(@RequestParam("id") Integer id){
        return companyRepo.getCompanyById(id);
    }

    @CrossOrigin
    @RequestMapping(value = "/getPersonById", method = RequestMethod.GET)
    public Person getPersonById(@RequestParam("id") Integer id){
        return personRepo.getPersonById(id);
    }

    @CrossOrigin
    @RequestMapping(value = "/getHoldingCompaniesById", method = RequestMethod.GET)
    public List<Company> getHoldingCompaniesById(@RequestParam("id") Integer id){
       List<CompanyOwnership> ownerships = companyRepo.getCompanyById(id).getParentCompanyRelationships();
       List<Company> comps = new ArrayList<Company>();
       for(CompanyOwnership own: ownerships){
           comps.add(companyRepo.getCompanyById(own.getParentCompanyId()));
       }
       return comps;
    }

    @CrossOrigin
    @RequestMapping(value = "/getSubCompaniesById", method = RequestMethod.GET)
            public List<Company> getSubCompaniesById(@RequestParam("id") Integer id){
        List<CompanyOwnership> subs = companyRepo.getCompanyById(id).getChildCompanyRelationships();
        List<Company> comps = new ArrayList<Company>();
        for(CompanyOwnership own: subs){
            comps.add(companyRepo.getCompanyById(own.getChildCompanyId()));
        }
        return comps;
    }

    @CrossOrigin
    @RequestMapping(value = "/getCompanyPeopleById", method = RequestMethod.GET)
    public List<Person> getCompanyPeopleById(@RequestParam("id") Integer id){
        List<PersonCompanyRelation> rels = companyRepo.getCompanyById(id).getPersonRelationships();
        List<Person> comps = new ArrayList<Person>();
        for(PersonCompanyRelation rel: rels){
            comps.add(personRepo.getPersonById(rel.getPersonId()));
        }
        return comps;
    }

    @CrossOrigin
    @RequestMapping(value = "/getRelatedCompaniesByPersonId", method = RequestMethod.GET)
    public List<Company> getRelatedCompaniesByPersonId(@RequestParam("id") Integer id){
        List<PersonCompanyRelation> rels = personRepo.getPersonById(id).getCompanyRelationships();
        List<Company> comps = new ArrayList<Company>();
        for(PersonCompanyRelation relation: rels){
            comps.add(companyRepo.getCompanyById(relation.getCompanyId()));
        }
        return comps;
    }

    @CrossOrigin
    @RequestMapping(value = "/getRelationshipFromCompanyAndPerson", method = RequestMethod.GET)
    public PersonCompanyRelation getRelationshipString(@RequestParam("personId") Integer personId, @RequestParam("companyId") Integer companyId){
        PersonCompanyRelation tempRel = personCompRelationRepo.getRelationshipByCompanyAndPerson(personId, companyId);
        return tempRel;
    }

    @CrossOrigin
    @RequestMapping(value = "/getTopDogByDirectOwnerId", method = RequestMethod.GET)
    public MainCompany getTopDogByDirectOwnerId(@RequestParam("companyId") Integer companyId){
        Company topDog = companyRepo.getCompanyById(companyId);
        List<CompanyOwnership> owners = topDog.getParentCompanyRelationships();
        while(owners.size() > 0){
            topDog = companyRepo.getCompanyById(owners.get(0).getParentCompanyId());
            owners = topDog.getParentCompanyRelationships();
        }
        return getMainCompanyFromCompany(topDog, companyId);
    }

    @CrossOrigin
    @RequestMapping(value = "/getMainCompanyByDirectOwnerId", method = RequestMethod.GET)
    public MainCompany getMainCompanyByDirectOwnerId(@RequestParam("companyId") Integer companyId){
        Company company = companyRepo.getCompanyById(companyId);
        return getMainCompanyFromCompany(company, companyId);
    }

    @CrossOrigin
    @RequestMapping(value = "/getAllCompaniesByDirectOwnerId", method = RequestMethod.GET)
    public List<MainCompany> getAllCompaniesByDirectOwnerId(@RequestParam("companyId") Integer companyId){
        List<MainCompany> companyList = new ArrayList<MainCompany>();
        Company topDog = companyRepo.getCompanyById(companyId);
        List<CompanyOwnership> owners = topDog.getParentCompanyRelationships();
        Integer lastId = companyId;
        companyList.add(getMainCompanyFromCompany(topDog, lastId));
        while(owners.size() > 0){
            topDog = companyRepo.getCompanyById(owners.get(0).getParentCompanyId());
            companyList.add(getMainCompanyFromCompany(topDog, lastId));
            owners = topDog.getParentCompanyRelationships();
            lastId = topDog.getId();
        }
        Collections.reverse(companyList);

        return companyList;
    }

    @CrossOrigin
    @RequestMapping(value = "/getMainPersonById", method = RequestMethod.GET)
    private MainPerson getMainPersonById(@RequestParam("id") Integer personId){
        Person tempPerson = personRepo.getPersonById(personId);
        MainPerson tempMainPerson = new MainPerson();

        List<PersonCompanyRelation> persRelations = tempPerson.getCompanyRelationships();
        List<String> mainPersRelations = new ArrayList<String>();
        for(int j = 0; j < persRelations.size(); j++) {
            mainPersRelations.add(companyRepo.getCompanyById(persRelations.get(j).getCompanyId()).getCompanyName());
        }

        tempMainPerson.setOtherCompanies(mainPersRelations);
        tempMainPerson.setName(tempPerson.getPersonName());
        tempMainPerson.setDescription(tempPerson.getDescription());
        return tempMainPerson;
    }

    private MainCompany getMainCompanyFromCompany(Company company, Integer compareId){
        MainCompany mainCompany = new MainCompany();
        List<MainPerson> companyPeople = new ArrayList<MainPerson>();
        List<PersonCompanyRelation> relationships = company.getPersonRelationships();
        List<PartialOwner> partialOwnerList = new ArrayList<>();
        List<PartialOwnerRelationships> partialRelations = company.getPartialOwners();

        for(int i = 0; i < relationships.size(); i++){
            Person tempPerson = personRepo.getPersonById(relationships.get(i).getPersonId());
            MainPerson tempMainPerson = new MainPerson();

            List<PersonCompanyRelation> persRelations = tempPerson.getCompanyRelationships();
            List<String> mainPersRelations = new ArrayList<String>();
            for(int j = 0; j < persRelations.size(); j++) {
                if (!persRelations.get(j).getCompanyId().equals(company.getId())) {
                    mainPersRelations.add(companyRepo.getCompanyById(persRelations.get(j).getCompanyId()).getCompanyName());
                }
            }

            tempMainPerson.setOtherCompanies(mainPersRelations);
            tempMainPerson.setName(tempPerson.getPersonName());
            tempMainPerson.setDescription(tempPerson.getDescription());
            tempMainPerson.setRelationship(relationships.get(i).getRelationship());
            tempMainPerson.setLink(tempPerson.getWikiLink());
            companyPeople.add(tempMainPerson);
        }

        for(int k = 0; k < partialRelations.size(); k++){
            PartialOwner partOwner = new PartialOwner();
            Company comp = companyRepo.getCompanyById(partialRelations.get(k).getPartialOwnerId());
            partOwner.setName(comp.getCompanyName());
            partOwner.setRelationship(partialRelations.get(k).getRelationship());
            partialOwnerList.add(partOwner);
        }

        List<CompanyOwnership> subsidiaryRelations = company.getChildCompanyRelationships();
        List<String> subsidiaries = new ArrayList<String>();
        for(int f = 0; f < subsidiaryRelations.size(); f++) {
            if (!subsidiaryRelations.get(f).getChildCompanyId().equals(compareId)) {
                subsidiaries.add(companyRepo.getCompanyById(subsidiaryRelations.get(f).getChildCompanyId()).getCompanyName());
            }
        }

        mainCompany.setLocation(company.getLocation());
        mainCompany.setName(company.getCompanyName());
        mainCompany.setMainPeople(companyPeople);
        mainCompany.setSubsidiaries(subsidiaries);
        mainCompany.setPartialOwners(partialOwnerList);

        return mainCompany;
    }

}
