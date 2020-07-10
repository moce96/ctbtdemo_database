import InsertData.RegionData;
import InsertData.b_Crew.CrewFoundation;
import InsertData.b_Crew.CrewHealth;
import InsertData.i_oceanfishing.*;
import InsertData.j_port.*;
import InsertData.l_project.*;
import InsertData.m_checkout.*;
import InsertData.*;
import InsertData.a_announce.*;
import InsertData.b_Crew.*;
import InsertData.c_company.*;
import InsertData.d_breed.*;
import InsertData.f_law.*;
import InsertData.g_engineering.*;
import InsertData.h_market.*;
import InsertData.i_oceanfishing.*;
import InsertData.j_port.*;
import InsertData.k_oceanpasture.*;
import InsertData.l_project.*;
import InsertData.m_checkout.*;
import InsertData.s_ship.*;
import InsertData.ship.ShipStakeholder;
import InsertData.u_user.*;
import random.MysqlRead;
import sun.applet.Main;
import utilClass.Investigation;

public class Solution {

    public static void main(String[] args) {





//        LawEnforcement.lawEnforcement();                  //执法数据
//        DangerCheck.investigation();                  //排查隐患数据
//        DisputeData.disputeData();                        //渔事纠纷
//        FisheryProtectedArea.fisheryProtectedArea();      //渔业保护区
//        RegionBW.regionBW();                              //区域黑白名单
//        ReleaseSituation.releaseSituation();              //增值流放情况a
//        RegionData.regionData();                            //区域基本信息

        /**
         *s_ship
         */
//        ShipFoundation.shipFoundation();      //船舶基础表
//        FishCatch.fishCatch();                //捕捞渔获表
//        OilPatch.oilPatch();                  //油补表
//        ShipCert.shipCert();                  //船舶证书表
//        ShipComprehensiveEval.shipComprehensiveEval();        //船舶综合审查表
//        ShipCredit.shipCredit();              //船舶信用表
//        ShipRenovation.shipRenovation();      //船舶更新改造表
//        ShipSecurityRecord.shipSecurityRecord();      //船舶安检记录表
//        ShipTerminalEquipment.shipTerminalEquipment();        //船舶终端设备表
//        ShipCrew.shipCrew();                  //船舶船员表
//        ShipGroup.shipGroup();                //船舶编组表
//        ShipLawResult.shipLawResult();          //船舶执法结果
//        ThreeNoShip.threeNoShip();              //三无船只a
//        ShipCirculation.shipCirculation();      //船舶流转记录表
//        ShipStakeholder.shipStakeholder();      //船舶干系人表
//
//
//        /**
//         * b_crew
//         */
//        CrewFoundation.crewFoundation();      //船员基础表
//        CrewHealth.crewHealth();              //船员健康表
//        CrewResume.crewResume();              //船员履历表
//        CrewComprehensiveEval.crewComprehensiveEval();        //人员综合审查表
//        CrewCert.crewCert();                  //船员证书表
//        CrewLawResult.crewCredit();              //船员执法结果
//        CrewInsurance.crewInsurance();          //船员保险表
//        CrewExam.crewExam();                    //船员考试表
//        CrewTraining.crewTraining();            //人员培训表
////
//        /**
//         * c_company
//         */
//        CompanyFoundation.companyFoundation();                    //公司基础表
//        CompanyComprehensiveEval.companyComprehensiveEval();      //公司综合审查表
//        CompanyLawResult.companyCredit();                         //公司执法结果
//
//        /**
//         * f_law
//         */
//        EnforceLawShipExpansion.enforceLawShipExpansion();        //执法船拓展表
//        EnforceLawCrew.enforceLawCrew();                          //执法人员表
//        EnforceLawAttendance.enforceLawAttendance();               //执法记录表
//
//        /**
//         * u_user
//         */
//        User.user();                                    //用户表
//        Role.role();                                    //角色表
//        Power.power();                                  //权限表
//
//
//
//        /**
//         * a_announce
//         */
//        AnnounceFoundation.announceFoundation();              //公告基础表
//        AnnounceRead.AnnounceRead();                          //用户是否阅读公告表
//        AnnounceConsultSituation.AnnounceConsultSituation();  //公告查阅情况表
//
//
//        /**
//         * g_engineering
//         *
//         *  g_engiDumpingRegion 倾废区域     g_engiMiningArea 开采区域
//         * 两张表手动输入
//         */
//        EngineeringConstruction.engineeringConstruction();      //工程建设表
//        EngineeringConsEvent.engineeringConsEvent();            //工程建设事件表
//        EngiMiningRecord.engiMiningRecord();                    //开采记录表
//        EngiDumpRecord.engiDumpRecord();                        //倾废记录表
//        EngiAudit.engiAudit();                                  //工程审批
//        EngiAuditConfirm.engiAuditConfirm();                    //工程审批确认
//        EngiPhoto.engiPhoto();                                  //工程影像采集表
//        ReclamationRecord.reclamationRecord();                  //围填海记录
//
//        /**
//         * d_breed
//         *
//         * d_breedWaterArea 养殖水域滩涂规划表已经手动填充
//         */
//        BreedPerson.breedPerson();                              //养殖人员表
//        BreedPersonCert.breedPersonCert();                      //养殖人员证书表
//        AquaBaseFoundation.aquaBaseFoundation();                //基地表
//        AquaBaseCert.aquaBaseCert();                            //基地证书表
//        CultureSituation.cultureSituation();                    //增殖情况表
//        CultureRecord.cultureRecord();                          //育苗记录表
//        CultureEnvironment.cultureEnvironment();                //养殖环境数据表
//        MedicalRecord.medicalRecord();                          //用药记录表
//        ProductionProcess.productionProcess();                  //生产过程表
//        FryDestination.fryDestination();                        //苗种去向表
//        FrySource.frySource();                                  //苗种来源表
//        DiseaseMonitor.diseaseMonitor();                        //疫病监测点表(后两个字段不知为啥报错)手动修改
//        Sensor.sensor();                                            //传感器表
//        SensorData.sensorData();                                //传感器数据表
//        DiseaseMonitorEquipment.diseaseMonitorEquipment();      //疫病监测设备表
//        DiseaseSituation.diseaseSituation();                    //疫病情况表
//        BreedReport.breedReport();                              //养殖及水产苗种情况上报表
//        BreedTailWater.breedTailWater();                        //养殖尾水情况表
//
//
//        /**
//         * h_market
//         */
//        MarketFoundation.marketFoundation();                    //市场交易基础表
//        MarketTransaction.marketTransaction();                  //市场交易交易情况表
//
//        /**
//         * i_oceanfishing
//         *
//         * i_hydrologicalData 远洋海洋水文数据  手动填充
//         */
//        OceanResources.oceanResources();                        //远洋资源表
//        SupplyBase.supplyBase();                                //补给基地表
//        FisheryData.fisheryData();                              //渔情数据
//        SupplyBaseStock.supplyBaseStock();                      //补给基地资源存量表
//        OceanShipFoundation.oceanShipFoundation();              //远洋船舶基础信息表（扩展表）
//        OceanCrewMedical.oceanCrewMedical();                    //远洋船员医疗表
//        OceanSpecificDeclaration.oceanSpecificDeclaration();    //远洋专项申请表
//        CompanyTalk.companyTalk();                              //远洋船舶与企业沟通表
//        EmergencyDrills.emergencyDrills();                      //远洋渔船应急演练情况表
//        PolicyPublish.policyPublish();                          //政策法规发布表
//
//        /**
//         * j_port
//         */
//        PortFoundation.portFoundation();                        //港口基本信息表
//        PortFunctionArea.portFunctionArea();                    //港口功能区划层
//        FunctionAreaShipStop.functionAreaShipStop();            //港口功能区停泊船舶列表
//        ArrivePort.arrivePort();                                //进港汇报表
//        LeavePort.leavePort();                                  //离港汇报表
//        BeaconFoundation.beaconFoundation();                    //航标基础表
//        BeaconSituation.beaconSituation();                       //航标状况表
//        CameraInformation.cameraInformation();                  //摄像头信息表


        /**
         * k_oceanpasture
         */
//        OceanPasture.oceanPasture();                        //海洋牧场
//        OceanPastureEvent.oceanPastureEvent();              //海洋牧场事件表a


        /**
         * l_project
         *
         * l_projectWorkType 项目业务类别表
         * (String[] workTypeId = {"101","102","103","104"};)
         * l_projectBudgetType 项目预算类别表
         * （String[] budgetTypeId = {"1001","1002","1003","1004","1005"};）
         * 手动填充
         */
//        ProjectStructCompany.projectStructCompany();        //项目建设单位表
//        ProjectSpecific.projectSpecific();                  //项目专项表（生成id之后其余的手动填写）
//        ProjectDeclarePerson.projectDeclarePerson();        //项目申报人表
//        ProjectBank.projectBank();                          //项目储备库
//        ExpertFoundation.expertFoundation();                //专家基础表
//        ExpertBank.expertBank();                            //专家库
//        ProjectDeclare.projectBank();                       //项目申报表
//        ProjectDetail.projectDetail();                      //项目详情表
//        ProjectExpenditure.projectExpenditure();            //项目支出明细
//        PerformanceGoal.performanceGoal();                  //绩效目标
//        ProjectEnclose.projectEnclose();                    //项目附件表
//        File.file();                                        //指导文件管理(不好造字段的话，可以手动填写)

        /**
         * m_checkout
         */
//        InspectItemsPrice.inspectItemsPrice();              //检验项目定价表
//        InspectApply.inspectApply();                        //检验申请表
//        InspectCertificate.inspectCertificate();            //检验证书表
//        InspectCertPrintHistory.inspectCertPrintHistory();  //检验证书打印历史表
//        InspectItemsChargingResult.inspectItemsChargingResult();    //检验项目收费结果
//        InspectShipBasicInformation.inspectShipBasicInformation();  //检验船舶基本信息表a
//        InspectItemResult.inspectItemResult();              //检验项目结果表a
//        ServiceInspectReport.serviceInspectReport();        //营运检验报告表a
//        InspectExpert.inspectExpert();                      //检测专家a
//        Enterprise.enterprise();                            //企业表a
//        Surveyor.surveyor();                                //验船师表a
//        ShipConstructAndMaintenance.shipConstructAndMaintenance();  //船舶建造维修表a
//        ShipyardCredit.shipyardCredit();                    //船厂信用表a
//        ShipyardMonitor.shipyardMonitor();                  //船厂监控设备表a




    }

}
