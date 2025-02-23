<?xml version="1.0" encoding="UTF-8"?>
<TestSuiteEntity>
   <description></description>
   <name>req res suite</name>
   <tag></tag>
   <isRerun>false</isRerun>
   <mailRecipient>aakash@qburst.com;</mailRecipient>
   <numberOfRerun>0</numberOfRerun>
   <pageLoadTimeout>30</pageLoadTimeout>
   <pageLoadTimeoutDefault>true</pageLoadTimeoutDefault>
   <rerunFailedTestCasesOnly>false</rerunFailedTestCasesOnly>
   <rerunImmediately>true</rerunImmediately>
   <testSuiteGuid>990387c3-e95d-4e7d-bfe7-fffb56104d85</testSuiteGuid>
   <testCaseLink>
      <guid>629327c3-4af1-4f30-b652-07d68723894c</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/reqres/TC001_listusers</testCaseId>
      <usingDataBindingAtTestSuiteLevel>false</usingDataBindingAtTestSuiteLevel>
   </testCaseLink>
   <testCaseLink>
      <guid>27332f11-194b-409b-8f1f-565434885e4e</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/reqres/TC002_getuserdata</testCaseId>
      <usingDataBindingAtTestSuiteLevel>false</usingDataBindingAtTestSuiteLevel>
   </testCaseLink>
   <testCaseLink>
      <guid>4335e18e-45af-4a75-9892-1ab7d19404a1</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/reqres/TC003_update user</testCaseId>
      <usingDataBindingAtTestSuiteLevel>false</usingDataBindingAtTestSuiteLevel>
   </testCaseLink>
   <testCaseLink>
      <guid>6713643e-a549-4fa0-adf6-7718d31041f7</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <iterationNameVariable>
         <defaultValue>'eva'</defaultValue>
         <description></description>
         <id>8fd6de78-394d-45e3-9f69-150f29d60a5a</id>
         <masked>false</masked>
         <name>name</name>
      </iterationNameVariable>
      <testCaseId>Test Cases/reqres/TC004_new user verify</testCaseId>
      <testDataLink>
         <combinationType>ONE</combinationType>
         <id>1ca37f78-4485-4424-b83a-afbdf5ef8ae1</id>
         <iterationEntity>
            <iterationType>ALL</iterationType>
            <value></value>
         </iterationEntity>
         <testDataId>Data Files/create user test data</testDataId>
      </testDataLink>
      <usingDataBindingAtTestSuiteLevel>true</usingDataBindingAtTestSuiteLevel>
      <variableLink>
         <testDataLinkId>1ca37f78-4485-4424-b83a-afbdf5ef8ae1</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>name</value>
         <variableId>8fd6de78-394d-45e3-9f69-150f29d60a5a</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>1ca37f78-4485-4424-b83a-afbdf5ef8ae1</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>job</value>
         <variableId>83a58043-d881-4ba9-ab67-173b478adae9</variableId>
      </variableLink>
   </testCaseLink>
</TestSuiteEntity>
