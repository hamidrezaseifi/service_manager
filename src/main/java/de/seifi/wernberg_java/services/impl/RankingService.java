package de.seifi.wernberg_java.services.impl;

import de.seifi.wernberg_java.entities.Tab02Entity;
import de.seifi.wernberg_java.entities.Tab03Entity;
import de.seifi.wernberg_java.enums.ServiceStatus;
import de.seifi.wernberg_java.models.Tab02ProcessModel;
import de.seifi.wernberg_java.repositories.Tab02Repository;
import de.seifi.wernberg_java.repositories.Tab03Repository;
import de.seifi.wernberg_java.services.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@Service
public class RankingService implements IService {

    private static final Logger logger = LoggerFactory.getLogger(RankingService.class);

    private final Tab02Repository tab02Repository;

    private final Tab03Repository tab03Repository;

    private final Tab01DataReader tab01DataReader;

    private Float minValueDif;

    private Float minValueRat;

    private Integer cutoffValue;

    private Float liqValue;

    private final List<String> lastProceedRows;

    private final List<String> errorList;

    private ServiceStatus status;

    public RankingService(Tab02Repository tab02Repository,
                          Tab03Repository tab03Repository,
                          Tab01DataReader tab01DataReader) {
        this.tab02Repository = tab02Repository;
        this.tab03Repository = tab03Repository;
        this.tab01DataReader = tab01DataReader;

        lastProceedRows = new ArrayList<>();

        errorList = new ArrayList<>();

        this.status = ServiceStatus.IDLE;

    }

    @Override
    public void run() {
        this.status = ServiceStatus.RUNNUNG;

        this.readBasicData();

        if(this.liqValue == 0){
            logger.info("There is 0 value in 6LIQ : L2!");

            this.status = ServiceStatus.STOPPED;
            return;
        }

        List<Tab02ProcessModel> notProceedList = this.doStepOne();
        if(notProceedList.isEmpty()){
            logger.info("There is no new data to process in Tab_02");

            this.status = ServiceStatus.STOPPED;
            return;

        }


        if(this.minValueDif > 0){
            notProceedList = this.doStepTwo(notProceedList);
        }

        if(notProceedList.isEmpty()){
            logger.info("Results of step 2 is empty!");

            this.status = ServiceStatus.STOPPED;
            return;
        }

        List<Tab02ProcessModel> sortedNotProceedList = this.doStepThree(notProceedList);
        if(sortedNotProceedList.isEmpty()){
            logger.info("Results of step 3 is empty!");

            this.status = ServiceStatus.STOPPED;
            return;
        }

        sortedNotProceedList = this.doStepFour(sortedNotProceedList);
        if(sortedNotProceedList.isEmpty()){
            logger.info("Results of step 4 is empty!");

            this.status = ServiceStatus.STOPPED;
            return;
        }

        sortedNotProceedList = this.doStepFiveSixSeven(sortedNotProceedList);
        if(sortedNotProceedList.isEmpty()){
            logger.info("Results of step 5-7 is empty!");

            this.status = ServiceStatus.STOPPED;
            return;
        }

        this.doStepEightNineSeven(sortedNotProceedList);

        this.status = ServiceStatus.STOPPED;

    }

    private void doStepEightNineSeven(List<Tab02ProcessModel> sortedNotProceedList) {
        Tab02ProcessModel firstRow = sortedNotProceedList.get(0);

        Float bmax = (firstRow.getVol1() + firstRow.getVol2()) * firstRow.getLatest() / this.liqValue;
        Tab03Entity entity = new Tab03Entity(firstRow.getId(), firstRow.getLs(), bmax);

        this.tab03Repository.deleteAll();
        this.tab03Repository.save(entity);
    }

    private List<Tab02ProcessModel> doStepFiveSixSeven(List<Tab02ProcessModel> sortedNotProceedList) {
        if(minValueRat > 0){
            sortedNotProceedList.forEach( m -> {
                m.calculate();
            });

            sortedNotProceedList = sortedNotProceedList.stream().
                                                       filter(m -> m.getCalcValue() >= minValueRat).
                                                       collect(Collectors.toList());

            sortedNotProceedList.sort((o1, o2) -> {
                if(o1.getCalcValue() == o2.getCalcValue()){
                    return 0;
                }
                else if(o1.getCalcValue() > o2.getCalcValue()){
                    return -1;
                }
                return 1;
            });

        }
        return sortedNotProceedList;
    }

    private List<Tab02ProcessModel> doStepFour(List<Tab02ProcessModel> sortedNotProceedList) {
        if(this.cutoffValue > 0){
            sortedNotProceedList = sortedNotProceedList.subList(0, cutoffValue);
        }

        return sortedNotProceedList;
    }

    private List<Tab02ProcessModel> doStepThree(List<Tab02ProcessModel> notProceedList) {

        notProceedList.sort((o1, o2) -> {
            if(o1.getMove1() == o2.getMove1()){
                return 0;
            }
            else if(o1.getMove1() > o2.getMove1()){
                return -1;
            }
            return 1;
        });

        return notProceedList;
    }

    private List<Tab02ProcessModel> doStepTwo(List<Tab02ProcessModel> notProceedList) {
        List<Tab02ProcessModel> results = notProceedList.stream().
                                                        filter(m -> m.getMove1() - m.getMove2() > this.minValueDif).
                                                        collect(Collectors.toList());

        return results;
    }

    private List<Tab02ProcessModel> doStepOne() {
        List<Tab02Entity> entities = this.tab02Repository.findAll();

        List<Tab02ProcessModel> notProceedList = new ArrayList<>();
        List<String> newIdList = new ArrayList<>();

        for(Tab02Entity entity: entities){
            Tab02ProcessModel model = new Tab02ProcessModel(entity);
            model.checkValues();

            String idVal = model.getIdentityValue();
            if(!this.lastProceedRows.contains(idVal)){
                notProceedList.add(model);
                newIdList.add(idVal);
            }
        }

        if(!newIdList.isEmpty()){
            this.lastProceedRows.clear();
            this.lastProceedRows.addAll(newIdList);

        }

        return notProceedList;
    }

    private void readBasicData() {
        tab01DataReader.reloadData();
        this.minValueDif = tab01DataReader.getDateFloat("3DIF", "l1");
        this.minValueRat = tab01DataReader.getDateFloat("2RAT", "l2");
        this.cutoffValue = tab01DataReader.getDateInteger("4CUT", "l1");
        this.liqValue = tab01DataReader.getDateFloat("6LIQ", "l1");
        
    }

    @Override
    public List<String> getErrorList() {
        return errorList;
    }

    @Override
    public ServiceStatus getStatus() {
        return this.status;
    }
}
