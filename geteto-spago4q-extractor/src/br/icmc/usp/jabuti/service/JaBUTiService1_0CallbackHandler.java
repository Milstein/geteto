
/**
 * JaBUTiService1_0CallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.1  Built on : Oct 19, 2009 (10:59:00 EDT)
 */

    package br.icmc.usp.jabuti.service;

    /**
     *  JaBUTiService1_0CallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class JaBUTiService1_0CallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public JaBUTiService1_0CallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public JaBUTiService1_0CallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for sendTraceFile method
            * override this method for handling normal response from sendTraceFile operation
            */
           public void receiveResultsendTraceFile(
                    br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.SendTraceFileResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from sendTraceFile operation
           */
            public void receiveErrorsendTraceFile(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getAllCoveredAndUncoveredRequiredElements method
            * override this method for handling normal response from getAllCoveredAndUncoveredRequiredElements operation
            */
           public void receiveResultgetAllCoveredAndUncoveredRequiredElements(
                    br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetAllCoveredAndUncoveredRequiredElementsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAllCoveredAndUncoveredRequiredElements operation
           */
            public void receiveErrorgetAllCoveredAndUncoveredRequiredElements(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getProjects method
            * override this method for handling normal response from getProjects operation
            */
           public void receiveResultgetProjects(
                    br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetProjectsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getProjects operation
           */
            public void receiveErrorgetProjects(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getInstrumentedProject method
            * override this method for handling normal response from getInstrumentedProject operation
            */
           public void receiveResultgetInstrumentedProject(
                    br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetInstrumentedProjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getInstrumentedProject operation
           */
            public void receiveErrorgetInstrumentedProject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getCoverageByCriteria method
            * override this method for handling normal response from getCoverageByCriteria operation
            */
           public void receiveResultgetCoverageByCriteria(
                    br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetCoverageByCriteriaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCoverageByCriteria operation
           */
            public void receiveErrorgetCoverageByCriteria(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getAllRequiredElements method
            * override this method for handling normal response from getAllRequiredElements operation
            */
           public void receiveResultgetAllRequiredElements(
                    br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetAllRequiredElementsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAllRequiredElements operation
           */
            public void receiveErrorgetAllRequiredElements(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for addTestCases method
            * override this method for handling normal response from addTestCases operation
            */
           public void receiveResultaddTestCases(
                    br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.AddTestCasesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from addTestCases operation
           */
            public void receiveErroraddTestCases(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for ignoreClasses method
            * override this method for handling normal response from ignoreClasses operation
            */
           public void receiveResultignoreClasses(
                    br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.IgnoreClassesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from ignoreClasses operation
           */
            public void receiveErrorignoreClasses(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getCoverageByMethods method
            * override this method for handling normal response from getCoverageByMethods operation
            */
           public void receiveResultgetCoverageByMethods(
                    br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetCoverageByMethodsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCoverageByMethods operation
           */
            public void receiveErrorgetCoverageByMethods(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getGraph method
            * override this method for handling normal response from getGraph operation
            */
           public void receiveResultgetGraph(
                    br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetGraphResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getGraph operation
           */
            public void receiveErrorgetGraph(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getMetrics method
            * override this method for handling normal response from getMetrics operation
            */
           public void receiveResultgetMetrics(
                    br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetMetricsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getMetrics operation
           */
            public void receiveErrorgetMetrics(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for selectClassesToInstrument method
            * override this method for handling normal response from selectClassesToInstrument operation
            */
           public void receiveResultselectClassesToInstrument(
                    br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.SelectClassesToInstrumentResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from selectClassesToInstrument operation
           */
            public void receiveErrorselectClassesToInstrument(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getCoverageByClasses method
            * override this method for handling normal response from getCoverageByClasses operation
            */
           public void receiveResultgetCoverageByClasses(
                    br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetCoverageByClassesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getCoverageByClasses operation
           */
            public void receiveErrorgetCoverageByClasses(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for deleteProject method
            * override this method for handling normal response from deleteProject operation
            */
           public void receiveResultdeleteProject(
                    br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.DeleteProjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from deleteProject operation
           */
            public void receiveErrordeleteProject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cleanProject method
            * override this method for handling normal response from cleanProject operation
            */
           public void receiveResultcleanProject(
                    br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.CleanProjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cleanProject operation
           */
            public void receiveErrorcleanProject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for createProject method
            * override this method for handling normal response from createProject operation
            */
           public void receiveResultcreateProject(
                    br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.CreateProjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from createProject operation
           */
            public void receiveErrorcreateProject(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getRequiredElementsByCriterion method
            * override this method for handling normal response from getRequiredElementsByCriterion operation
            */
           public void receiveResultgetRequiredElementsByCriterion(
                    br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetRequiredElementsByCriterionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getRequiredElementsByCriterion operation
           */
            public void receiveErrorgetRequiredElementsByCriterion(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for updateProject method
            * override this method for handling normal response from updateProject operation
            */
           public void receiveResultupdateProject(
                    br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.UpdateProjectResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from updateProject operation
           */
            public void receiveErrorupdateProject(java.lang.Exception e) {
            }
                


    }
    