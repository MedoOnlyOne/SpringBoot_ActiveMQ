const js2xmlparser = require('js2xmlparser');
const fetch = require('node-fetch');

const send = async () => {

    const body = `<?xml version="1.0" encoding="UTF-8"?>
    <APILogEntry xmlns:json="http://www.ibm.com/xmlns/prod/2009/jsonx">
     <ReqID>REQTestFilesThread04</ReqID>
     <AuditRecord>
      <APIDetails>
       <APIName>accounts-links</APIName>
       <APIVersion>1.0.0</APIVersion>
       <APIType>REST</APIType>
       <HTTPMethod>POST</HTTPMethod>
       <Protocol>HTTPS</Protocol>
       <APIRoot>accounts-information/v1</APIRoot>
       <OperationID>TPP-CreateAccountsLink</OperationID>
       <APIPath>/accounts-links</APIPath>
       <CatalogID>5b3ce9c9-a36e-4413-b4b2-2330696f9cbc</CatalogID>
       <CatalogName>newtek-tsp-dev</CatalogName>
       <ClientOrgID>aafe547b-40e1-4446-976c-fe962e5db8c3</ClientOrgID>
       <ClientOrgName>test-org</ClientOrgName>
       <ClientAppID>9a31ad375eceda82749a3f01c2fce5c8</ClientAppID>
       <ClientAppName>test-app</ClientAppName>
      </APIDetails>
      <AuditVars>
       <Tmstmp1>2022-11-20T15:03:41.267Z</Tmstmp1>
       <Tmstmp2/>
       <Tmstmp3/>
       <Tmstmp4>2022-11-20T15:03:41.269Z</Tmstmp4>
       <TmstmpX/>
       <RejectionReason>Internal Error</RejectionReason>
       <HTTPStatusCode>500</HTTPStatusCode>
       <UsrDef1/>
       <UsrDef2/>
       <UsrDef3/>
       <UsrDef4/>
       <UsrDef5/>
       <UsrDef6/>
       <UsrDef7>192.168.210.195</UsrDef7>
       <UsrDef8>196c5565637a179d000deb21</UsrDef8>
       <UsrDef9/>
       <UsrDef10/>
       <UsrDef11/>
       <UsrDef12/>
       <UsrDef13/>
       <UsrDef14/>
       <UsrDef15/>
      </AuditVars>
     </AuditRecord>
     <DumpRecords>
      <Msg>
       <MsgType>REQ</MsgType>
       <Tmstmp>2022-11-20T15:03:41.267Z</Tmstmp>
       <ProviderName/>
       <HeaderParams>{"Authorization":"Bearer AAIgOWEzMWFkMzc1ZWNlZGE4Mjc0OWEzZjAxYzJmY2U1YzjT5NUsOcZagiI9wBgIfK8bFDlgGroWl0VSfK2XysdZ4F9D00qFK2bOm2JQo2AcqI2mBwPLY-hj76vi8UWuc9F9sGgZ9Bh5MG3gsBt7i6L2ZOU6MxKuw-IaEHYJHtSog6g","Content-Type":"application/json","User-Agent":"PostmanRuntime/7.29.2","Accept":"*/*","Postman-Token":"d0deecdc-9bc5-4d8a-bff2-97da855c48f1","Host":"apic10-gw-01.ejada.com:9445","Accept-Encoding":"gzip, deflate, br","Content-Length":"772","Cookie":"BIGipServerAPI-Factory-9443.app~API-Factory-9443_pool=!rvz8wWsNonya/DYCYjzg2gly7iNrMb14CR6D27p0ntgrdmG9+0zwBUytBTZpSUAx+Pa/AnV9gzC4xg=="}</HeaderParams>
       <QueryParams/>
       <PathParams>{}</PathParams>
       <Payload>7B0A20202244617461223A7B0A202020202246696E616E6369616C496E737469747574696F6E4964223A22524A484953415249222C0A2020202022536563757269747950726F66696C65223A225265646972656374696F6E222C0A20202020224461746147726F757073223A5B0A202020202020224163636F756E7444657461696C73220A202020205D2C0A20202020225053554964223A22323535222C0A2020202022557365724C6F67696E4964223A22524A4849534152495F222C0A202020202245787069726174696F6E4461746554696D65223A22323032322D31312D32305431313A35393A31325A222C0A20202020225472616E73616374696F6E46726F6D4461746554696D65223A22323032322D31312D30315431313A35393A31325A222C0A20202020225472616E73616374696F6E546F4461746554696D65223A22323032322D31312D32305431313A35393A31325A222C0A20202020224163636F756E7454797065223A22427573696E657373222C0A20202020224163636F756E7453756254797065223A2243757272656E744163636F756E74222C0A20202020224F6E426568616C664F66223A7B0A2020202020202254726164696E674E616D65223A2241636D65204163636F756E74696E672054726164696E67204E616D65222C0A202020202020224C6567616C4E616D65223A2241636D65204163636F756E74696E67204C6567616C204E616D65222C0A202020202020224964656E74696669657254797065223A224F74686572222C0A202020202020224964656E746966696572223A226162636431323334220A202020207D2C0A2020202022507572706F7365223A224163636F756E74204167677265676174696F6E220A20207D0A7D</Payload>
      </Msg>
      <Msg>
       <MsgType>RPLY</MsgType>
       <Tmstmp>2022-11-20T15:03:41.269Z</Tmstmp>
       <ProviderName/>
       <HeaderParams>{"x-request-id":"1964d30d-db3a-8be4-2d14-5220a60c2c46","Content-Type":"application/json"}</HeaderParams>
       <QueryParams/>
       <PathParams/>
       <Payload>7B0A20202268747470436F6465223A22353030222C0A202022687474704D657373616765223A22496E7465726E616C20536572766572204572726F72222C0A2020226D6F7265496E666F726D6174696F6E223A22496E7465726E616C20536572766572204572726F72222C0A202022436F6465223A224E5453502E4552524F522E3530302E303031222C0A2020224D657373616765223A2247656E6572616C20536572766572204572726F72220A7D</Payload>
      </Msg>
      <Msg>
       <MsgType>ERROR</MsgType>
       <Tmstmp>2022-11-20T15:03:41.269Z</Tmstmp>
       <ProviderName/>
       <HeaderParams>{"x-request-id":"1964d30d-db3a-8be4-2d14-5220a60c2c46","Content-Type":"application/json"}</HeaderParams>
       <QueryParams/>
       <PathParams/>
       <Payload>7B22726573706F6E7365223A7B22636F6465223A3530302C226D657373616765223A224572726F72222C2261646472657373223A223139322E3136382E3231302E313935222C22617069223A7B22656E64706F696E74223A7B2261646472657373223A223139322E3136382E3231302E313935222C22686F73746E616D65223A226170696331302D67772D30312E656A6164612E636F6D227D2C226F7065726174696F6E223A7B226964223A225450502D4372656174654163636F756E74734C696E6B222C2270617468223A222F6163636F756E74732D6C696E6B73227D2C2274797065223A2252455354222C226E616D65223A226163636F756E74732D6C696E6B73222C226964223A2262306363346330632D346636342D343364322D396538312D663866343639336365353238222C2276657273696F6E223A22312E302E30222C22726F6F74223A226163636F756E74732D696E666F726D6174696F6E2F7631222C226F7267223A7B226964223A2235623363653963392D613336652D343431332D623462322D323333303639366639636263222C226E616D65223A22747370747070227D2C22636174616C6F67223A7B226964223A2236383731373063642D616333612D343661302D626265632D623761643364353130663365222C226E616D65223A226E657774656B2D7473702D646576222C2270617468223A226E657774656B2D7473702D646576227D2C2270726F70657274696573223A7B226765746163636F756E74736C696E6B73223A22687474703A2F2F35322E35312E3231362E33363A31373639342F6765742D6163636F756E74732D6C696E6B732F7631222C226C6973746163636F756E74736C696E6B73223A22687474703A2F2F35322E35312E3231362E33363A31373639302F6C6973742D6163636F756E74732D6C696E6B73222C226372656174656163636F756E74736C696E6B73223A22687474703A2F2F35322E35312E3231362E33363A31373639352F6372656174652D6163636F756E74732D6C696E6B732F76312F6163636F756E74732D6C696E6B732F222C227265766F6B656163636F756E74736C696E6B73223A22687474703A2F2F35322E35312E3231362E33363A31373639322F7265766F6B652D6163636F756E74732D6C696E6B732F7631227D2C22636F6D7061746962696C697479223A7B227365637572697479223A7B2272657475726E2D76352D726573706F6E736573223A66616C73652C22636F70792D69642D686561646572732D746F2D6D657373616765223A66616C73657D2C22656E666F7263652D72657175697265642D706172616D73223A747275652C22777261707065722D706F6C6963696573223A7B22616C6C6F772D6368756E6B65642D75706C6F616473223A747275657D7D7D2C22636C69656E74223A7B22617070223A7B226964223A223961333161643337356563656461383237343961336630316332666365356338222C226E616D65223A22746573742D617070222C2274797065223A22222C226C6966656379636C652D7374617465223A2250524F44554354494F4E227D2C226F7267223A7B226964223A2261616665353437622D343065312D343434362D393736632D666539363265356462386333222C226E616D65223A22746573742D6F7267227D2C227469746C65223A2243726564656E7469616C20666F72205465737420417070227D2C2273797374656D223A7B226461746574696D65223A22323032322D31312D32305431353A30333A3431222C226461746574696D652D757463223A22323032322D31312D32305431323A30333A34312E3236345A222C2274696D657A6F6E65223A222B30333A3030222C2274696D65223A7B22686F7572223A31352C226D696E757465223A332C227365636F6E6473223A34317D2C2264617465223A7B226461792D6F662D7765656B223A372C226461792D6F662D6D6F6E7468223A32302C226D6F6E7468223A31312C2279656172223A323032327D7D2C224572726F724E616D65223A224A6176615363726970744572726F72222C224572726F724D657373616765223A22496E7465726E616C204572726F72222C22457863657074696F6E223A7B226E616D65223A224A6176615363726970744572726F72222C226D657373616765223A22496E7465726E616C204572726F72222C22737461747573223A7B22636F6465223A3530302C22726561736F6E223A22496E7465726E616C20536572766572204572726F72227D2C22706F6C6963795469746C65223A2256616C69646174652052657175657374227D7D7D</Payload>
      </Msg>
     </DumpRecords>
    </APILogEntry>`

    for(let i = 0; i < 10000; i++) {
        await fetch(' http://localhost:8080/send', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/xml'
            },
            body: body
        });
    }
};

send();