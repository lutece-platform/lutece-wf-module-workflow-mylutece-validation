/*
 * Copyright (c) 2002-2009, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.workflow.modules.taskmylutecevalidation.business;


import fr.paris.lutece.plugins.mylutece.modules.directory.authentication.business.MyluteceDirectoryUser;
import fr.paris.lutece.plugins.mylutece.modules.directory.authentication.business.MyluteceDirectoryUserHome;
import fr.paris.lutece.plugins.mylutece.modules.directory.authentication.service.MyluteceDirectoryPlugin;
import fr.paris.lutece.plugins.workflow.business.ResourceHistory;
import fr.paris.lutece.plugins.workflow.business.ResourceHistoryHome;
import fr.paris.lutece.plugins.workflow.business.task.Task;
import fr.paris.lutece.plugins.workflow.utils.WorkflowUtils;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.util.ReferenceList;
import fr.paris.lutece.util.html.HtmlTemplate;

import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;


/**
 *
 *TaskComment
 */
public class TaskMyluteceValidation extends Task
{
    //templates
    private static final String TEMPLATE_TASK_MYLUTECE_VALIDATION_CONFIG = "admin/plugins/workflow/modules/taskmylutecevalidation/task_config.html";
    

    /*
     * (non-Javadoc)
     * @see fr.paris.lutece.plugins.workflow.business.task.ITask#init()
     */
    public void init(  )
    {
    }

    /*
     * (non-Javadoc)
     * @see fr.paris.lutece.plugins.workflow.business.task.ITask#doSaveConfig(javax.servlet.http.HttpServletRequest, java.util.Locale, fr.paris.lutece.portal.service.plugin.Plugin)
     */
    public String doSaveConfig( HttpServletRequest request, Locale locale, Plugin workflowPlugin )
    {      

        return null;
    }

    /*
     * (non-Javadoc)
     * @see fr.paris.lutece.plugins.workflow.business.task.ITask#doValidateTask(int, java.lang.String, javax.servlet.http.HttpServletRequest, java.util.Locale, fr.paris.lutece.portal.service.plugin.Plugin)
     */
    public String doValidateTask( int nIdResource, String strResourceType, HttpServletRequest request, Locale locale,
        Plugin plugin )
    {        
        return null;
    }

    /*
     * (non-Javadoc)
     * @see fr.paris.lutece.plugins.workflow.business.task.ITask#getDisplayConfigForm(javax.servlet.http.HttpServletRequest, fr.paris.lutece.portal.service.plugin.Plugin, java.util.Locale)
     */
    public String getDisplayConfigForm( HttpServletRequest request, Plugin workflowPlugin, Locale locale )
    {
    	HashMap<String, Object> model = new HashMap<String, Object>(  );
       
        HtmlTemplate template = AppTemplateService.getTemplate( TEMPLATE_TASK_MYLUTECE_VALIDATION_CONFIG, locale, model );

        return template.getHtml(  );
    }

    /*
     * (non-Javadoc)
     * @see fr.paris.lutece.plugins.workflow.business.task.ITask#getDisplayTaskForm(int, java.lang.String, javax.servlet.http.HttpServletRequest, fr.paris.lutece.portal.service.plugin.Plugin, java.util.Locale)
     */
    public String getDisplayTaskForm( int nIdResource, String strResourceType, HttpServletRequest request,
        Plugin plugin, Locale locale )
    {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see fr.paris.lutece.plugins.workflow.business.task.ITask#getDisplayTaskInformation(int, javax.servlet.http.HttpServletRequest, fr.paris.lutece.portal.service.plugin.Plugin, java.util.Locale)
     */
    public String getDisplayTaskInformation( int nIdHistory, HttpServletRequest request, Plugin plugin, Locale locale )
    {       
        return null;
    }

    /*
     * (non-Javadoc)
     * @see fr.paris.lutece.plugins.workflow.business.task.ITask#processTask(int, javax.servlet.http.HttpServletRequest, fr.paris.lutece.portal.service.plugin.Plugin, java.util.Locale)
     */
    public void processTask( int nIdResourceHistory, HttpServletRequest request, Plugin workflowPlugin, Locale locale )
    {       
    	Plugin myluteceDirectoryPlugin = PluginService.getPlugin( MyluteceDirectoryPlugin.PLUGIN_NAME );
    	ResourceHistory resourceHistory = ResourceHistoryHome.findByPrimaryKey( nIdResourceHistory, workflowPlugin );
        
    	//activate the user
    	MyluteceDirectoryUser user = MyluteceDirectoryUserHome.findByPrimaryKey( resourceHistory.getIdResource(  ), myluteceDirectoryPlugin );
    	if( user != null )
    	{
    		user.setActivated( true );
        	MyluteceDirectoryUserHome.update( user, myluteceDirectoryPlugin ); 
    	}    	   	
    }

    /*
     * (non-Javadoc)
     * @see fr.paris.lutece.plugins.workflow.business.task.ITask#doRemoveConfig(fr.paris.lutece.portal.service.plugin.Plugin)
     */
    public void doRemoveConfig( Plugin workflowPlugin )
    {
       
    }

    /*
     * (non-Javadoc)
     * @see fr.paris.lutece.plugins.workflow.business.task.ITask#isConfigRequire()
     */
    public boolean isConfigRequire(  )
    {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see fr.paris.lutece.plugins.workflow.business.task.ITask#isFormTaskRequire()
     */
    public boolean isFormTaskRequire(  )
    {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see fr.paris.lutece.plugins.workflow.business.task.ITask#doRemoveTaskInformation(int, fr.paris.lutece.portal.service.plugin.Plugin)
     */
    public void doRemoveTaskInformation( int nIdHistory, Plugin plugin )
    {
       
    }

    public void doRemoveTaskInformation( Plugin plugin )
    {
    }

    /*
     * (non-Javadoc)
     * @see fr.paris.lutece.plugins.workflow.business.task.ITask#getTaskInformationXml(int, javax.servlet.http.HttpServletRequest, fr.paris.lutece.portal.service.plugin.Plugin, java.util.Locale)
     */
    public String getTaskInformationXml( int idHistory, HttpServletRequest request, Plugin plugin, Locale locale )
    {        
        return null;
    }

    /*
     * (non-Javadoc)
     * @see fr.paris.lutece.plugins.workflow.business.task.ITask#getTitle(fr.paris.lutece.portal.service.plugin.Plugin, java.util.Locale)
     */
    public String getTitle( Plugin workflowPlugin, Locale locale )
    {    	

    	return WorkflowUtils.EMPTY_STRING;
    }

    /*
     * (non-Javadoc)
     * @see fr.paris.lutece.plugins.workflow.business.task.ITask#getTaskFormEntries(fr.paris.lutece.portal.service.plugin.Plugin, java.util.Locale)
     */
    public ReferenceList getTaskFormEntries( Plugin plugin, Locale locale )
    {        
        return null;
    }

    /*
     * (non-Javadoc)
     * @see fr.paris.lutece.plugins.workflow.business.task.ITask#isTaskForActionAutomatic()
     */
    public boolean isTaskForActionAutomatic(  )
    {
        return true;
    }

}
