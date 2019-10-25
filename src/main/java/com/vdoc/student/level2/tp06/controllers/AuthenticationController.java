
package com.vdoc.student.level2.tp06.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.axemble.vdoc.sdk.Modules;
import com.axemble.vdoc.sdk.controllers.BaseController;
import com.axemble.vdoc.sdk.interfaces.IUser;
import com.axemble.vdoc.sdk.interfaces.runtime.IExecutionContext;
import com.axemble.vdoc.sdk.interfaces.runtime.IExecutionContext.IRequest;
import com.axemble.vdoc.sdk.modules.IDirectoryModule;
import com.axemble.vdoc.sdk.modules.IPortalModule;
import com.axemble.vdoc.sdk.utils.Logger;

/**
 * Created on 29 mars 2011
 * @author vdoc
 * 
 */
public class AuthenticationController extends BaseController
{
    protected static final Logger log = Logger.getLogger(AuthenticationController.class);

    /**
     * @see com.axemble.vdp.ui.framework.foundation.controllers.CustomController#parseRequest(com.axemble.vdp.ui.framework.runtime.IExecutionContext.IRequest)
     */
    public void parseRequest(IRequest iRequest) throws IOException
    {
        
    }
    
    /**
     * @see com.axemble.vdoc.sdk.controllers.BaseController#doProcess(com.axemble.vdp.ui.framework.runtime.IExecutionContext)
     */
    @Override
    public IExecutionContext doProcess(IExecutionContext ec) throws IOException
    {
        Document document = null;
        IPortalModule portalModule = null;
        IDirectoryModule iDirectoryModule = null;
        try
        {
            //TODO We must get a portalModule and a directoryModule
                // Portal module will be used to check authentication and directory module to get user's groups

            //TODO Get request parameters "login" and "password" (you can access the request in execution context)
            
            //TODO Try to get the user by login using the directorymodule
            
            //TODO After, call the buildXML method with parameters : user and authentication status
                //Tips : to check authentication you can use the method "verifyAuthentication" on portalmodule
            
            buildResponse(ec, "response.xml", document);
        }
        catch (Exception e)
        {
            String message = e.getMessage();
            if (message == null)
            {
                message = "";
            }
            log.error("Error in AuthenticationController buildResponse method : " + e.getClass() + " - " + message);        
        }
        finally
        {
            // We release opened modules
            Modules.releaseModule(iDirectoryModule);
            Modules.releaseModule(portalModule);
        }
        return ec;
    }
    
    /**
     * 
     * @param iUser
     * @param authenticated
     * @return
     * @throws Exception 
     */
    protected Document buildXML(IUser iUser, boolean authenticated) throws Exception
    {
        Document document = null;
        try
        {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            document = builder.newDocument();

            document.setXmlVersion("1.0");
            document.setXmlStandalone(true);

            // If user is not in directory or disabled
            if ( (iUser == null) || (!iUser.isEnable()) )
            {
                Element autorization = document.createElement("autorization");
                autorization.setAttribute("value", "0");
                document.appendChild(autorization);
            }
            else
            {
                // We can send information about user
                int autorizationValue = 2;
                if (authenticated)
                {
                    autorizationValue = 3;
                }
                
                Element autorization = document.createElement("autorization");
                autorization.setAttribute("value", Integer.toString(autorizationValue));
                
                Element login = document.createElement("login");
                login.setTextContent(iUser.getLogin());
                autorization.appendChild(login);
                
                Element lastName = document.createElement("last_name");
                lastName.setTextContent(iUser.getLastName());
                autorization.appendChild(lastName);
                
                Element firstName = document.createElement("first_name");
                firstName.setTextContent(iUser.getFirstName());
                autorization.appendChild(firstName);
                
                Element email = document.createElement("email");
                email.setTextContent(iUser.getEmail());
                autorization.appendChild(email);
                
                Element company = document.createElement("company");
                company.setTextContent(iUser.getOrganization().getName());
                autorization.appendChild(company);
                
                document = addGroupsNode(document, autorization, iUser);
                
                document.appendChild(autorization);
            }
        }
        catch (Exception e)
        {
            String message = e.getMessage();
            if (message == null)
            {
                message = "";
            }
            log.error("Error in AuthenticationController buildXML method : " + e.getClass() + " - " + message);
            throw(e);
        }
        return document;
    }
    
    /**
     * 
     * @param document 
     * @param iUser
     * @return
     */
    protected Document addGroupsNode(Document document, Element autorization, IUser iUser)
    {
        try
        {
            Element groups = document.createElement("groups");
            autorization.appendChild(groups);
            
            //TODO Here we must get groups of this user and for each
                // - Build a new element : document.createElement("group");
                // - Set "textcontent" of this element
                // - Add this new element to element "groups"
        }
        catch (Exception e)
        {
            String message = e.getMessage();
            if (message == null)
            {
                message = "";
            }
            log.error("Error in AuthenticationController buildResponse method : " + e.getClass() + " - " + message);
        }
        return document;
    }
    
    /**
     * 
     * @param ec
     * @param fileName
     * @param document
     * @throws Exception
     */
    protected void buildResponse(IExecutionContext ec, String fileName, Document document) throws Exception
    {
        try
        {
            DOMSource domSource = new DOMSource(document);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            StreamResult streamResult = new StreamResult(baos);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(domSource, streamResult);
            baos.flush();
            baos.close();
            
            DataInputStream xmlstream = new DataInputStream(new ByteArrayInputStream(baos.toByteArray()));
            OutputStream sos = ec.getResponse().getOutputStream();
            ec.getResponse().setHeader("Cache-Control", "Must-revalidate; Proxy-revalidate");
            ec.getResponse().setHeader("Pragma", "No-cache");
            ec.getResponse().setContentType("text/xml");
            BufferedInputStream in = new BufferedInputStream(xmlstream);
            BufferedOutputStream out = new BufferedOutputStream(sos);
            int b;
            while((b = in.read()) != -1) 
                out.write(b);
            out.flush();
            out.close();
            in.close();
        }
        catch (Exception e)
        {
            String message = e.getMessage();
            if (message == null)
            {
                message = "";
            }
            log.error("Error in AuthenticationController buildResponse method : " + e.getClass() + " - " + message);
            throw(e);
        }
    }

}
