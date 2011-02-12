/* ***** BEGIN LICENSE BLOCK *****
 *
 * Copyright (c) 2005-2007 Universidade de Sao Paulo, Sao Carlos/SP, Brazil.
 * All Rights Reserved.
 *
 * This file is part of Projection Explorer (PEx).
 *
 * How to cite this work:
 *  
@inproceedings{paulovich2007pex,
author = {Fernando V. Paulovich and Maria Cristina F. Oliveira and Rosane 
Minghim},
title = {The Projection Explorer: A Flexible Tool for Projection-based 
Multidimensional Visualization},
booktitle = {SIBGRAPI '07: Proceedings of the XX Brazilian Symposium on 
Computer Graphics and Image Processing (SIBGRAPI 2007)},
year = {2007},
isbn = {0-7695-2996-8},
pages = {27--34},
doi = {http://dx.doi.org/10.1109/SIBGRAPI.2007.39},
publisher = {IEEE Computer Society},
address = {Washington, DC, USA},
}
 *  
 * PEx is free software: you can redistribute it and/or modify it under 
 * the terms of the GNU General Public License as published by the Free 
 * Software Foundation, either version 3 of the License, or (at your option) 
 * any later version.
 *
 * PEx is distributed in the hope that it will be useful, but WITHOUT 
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License 
 * for more details.
 *
 * This code was developed by members of Computer Graphics and Image
 * Processing Group (http://www.lcad.icmc.usp.br) at Instituto de Ciencias
 * Matematicas e de Computacao - ICMC - (http://www.icmc.usp.br) of 
 * Universidade de Sao Paulo, Sao Carlos/SP, Brazil. The initial developer 
 * of the original code is Fernando Vieira Paulovich <fpaulovich@gmail.com>.
 *
 * Contributor(s): Roberto Pinho <robertopinho@yahoo.com.br>, 
 *                 Rosane Minghim <rminghim@icmc.usp.br>
 *
 * You should have received a copy of the GNU General Public License along 
 * with PEx. If not, see <http://www.gnu.org/licenses/>.
 *
 * ***** END LICENSE BLOCK ***** */

package visualizer.wizard;

import visualizer.graph.Graph;
import visualizer.projection.GraphBuilder;
import visualizer.projection.ProjectionData;
import visualizer.projection.ProjectionFactory;
import visualizer.projection.SourceType;
import visualizer.projection.distance.DissimilarityType;

/**
 *
 * @author Fernando Vieira Paulovich
 */
public class ProjectionWizardCore {

	private Graph graph;
    private GraphBuilder builder;

	
	//Current state of wizard
    private int currentState = INITIAL_STATE;
    //Possible directions to follow in the wizard
    public static final int NEXT_STATE = 0;
    public static final int PREVIOUS_STATE = 1;
    //States of create projection wizard
    private static final int INITIAL_STATE = 0;
    private static final int SOURCE_STATE = 1;
    private static final int PROJ_DIST_STATE = 2;
    private static final int PRE_PROC_STATE = 3;
    private static final int DIMEN_RED_STATE = 4;
    private static final int GEN_PARAM_STATE = 5;
    private static final int PROJECT_STATE = 6;
    
    //Views of each state
    private ProjectionDistanceChoice projDistView;
    private DataSourceChoice sourceView;
    private ProjectionView projView;
    private Preprocessing preprocessView;
    private GeneralParameters generalParametersView;
    private DimensionReduction dimensionReductionView;
	
    public ProjectionWizardCore(Graph graph) {
        this.graph = graph;
        initViews();
    }

    private void initViews()
    {
    	sourceView = new DataSourceChoice(graph.getProjectionData());
    	projDistView = new ProjectionDistanceChoice(graph.getProjectionData());
    	preprocessView = new Preprocessing(graph.getProjectionData());
    	generalParametersView = new GeneralParameters(graph.getProjectionData());
    	dimensionReductionView = new DimensionReduction(graph.getProjectionData());
    }
    
    public WizardPanel getNextPanel(int direction) {
        ProjectionData pdata = graph.getProjectionData();
        WizardPanel nextWizard = null;
        
        if (currentState != ProjectionWizardCore.INITIAL_STATE) {
        	if (direction != ProjectionWizardCore.NEXT_STATE && direction != ProjectionWizardCore.PREVIOUS_STATE) {
        		throw new IllegalArgumentException("Invalid direction.");
        	}
        }

        switch (currentState) {
            case ProjectionWizardCore.INITIAL_STATE:
                // Initial -> source
                currentState = ProjectionWizardCore.SOURCE_STATE;
                sourceView.reset();
                nextWizard = sourceView;
                break;

            case ProjectionWizardCore.SOURCE_STATE:
                if (direction == ProjectionWizardCore.PREVIOUS_STATE) {
                	assert false;
                }

            	// Source -> projection+distance
                if (direction == ProjectionWizardCore.NEXT_STATE) {
                    currentState = ProjectionWizardCore.PROJ_DIST_STATE;
                    projDistView.reset();
                    nextWizard = projDistView;
                }
                break;

            case ProjectionWizardCore.PROJ_DIST_STATE:
                // Projection+distance -> source
                if (direction == ProjectionWizardCore.PREVIOUS_STATE) {
                    currentState = ProjectionWizardCore.SOURCE_STATE;
                    sourceView.reset();
                    nextWizard = sourceView;
                }
                
                if (direction == ProjectionWizardCore.NEXT_STATE) {
                    if ((pdata.getSourceType() == SourceType.CORPUS || pdata.getSourceType() == SourceType.BIBTEX) && pdata.getDissimilarityType() != DissimilarityType.KOLMOGOROV) {
                        // Projection+distance -> pre-processing
                        currentState = ProjectionWizardCore.PRE_PROC_STATE;
                        nextWizard = preprocessView;
                    }

                    if (pdata.getSourceType() == SourceType.POINTS) {
                        // projection+distance -> dimension reduction
                        currentState = ProjectionWizardCore.DIMEN_RED_STATE;
                        dimensionReductionView.reset();
                        nextWizard = dimensionReductionView;
                    }
                    
                    if (pdata.getSourceType() == SourceType.DISTANCE_MATRIX) {
                        // projection+distance -> general parameters
                        currentState = ProjectionWizardCore.GEN_PARAM_STATE;
                        generalParametersView.reset();
                        nextWizard = generalParametersView;
                    }
                }
                break;

            case ProjectionWizardCore.PRE_PROC_STATE:
                if (direction == ProjectionWizardCore.PREVIOUS_STATE) {
                    // pre-processing -> projection+distance
                    currentState = ProjectionWizardCore.PROJ_DIST_STATE;
                    projDistView.reset();
                    nextWizard = projDistView;
                }
                
                if (direction == ProjectionWizardCore.NEXT_STATE) {
                    // pre-processing -> dimension reduction
                    currentState = ProjectionWizardCore.DIMEN_RED_STATE;
                    dimensionReductionView.reset();
                    nextWizard = dimensionReductionView;
                }
                break;

            case ProjectionWizardCore.DIMEN_RED_STATE:
                if (direction == ProjectionWizardCore.PREVIOUS_STATE) {
                    if ((pdata.getSourceType() == SourceType.CORPUS || pdata.getSourceType() == SourceType.BIBTEX) && pdata.getDissimilarityType() != DissimilarityType.KOLMOGOROV) {
                        //dimension reduction -> pre-processing
                        currentState = ProjectionWizardCore.PRE_PROC_STATE;
                        nextWizard = preprocessView;
                    } else {
                        // dimension reduction -> projection+distance
                        currentState = ProjectionWizardCore.PROJ_DIST_STATE;
                        projDistView.reset();
                        nextWizard = projDistView;
                    }
                }
                
                if (direction == ProjectionWizardCore.NEXT_STATE) {
                    //dimension reduction -> general parameters
                    currentState = ProjectionWizardCore.GEN_PARAM_STATE;
                    generalParametersView.reset();
                    nextWizard = generalParametersView;
                }
                
                break;

            case ProjectionWizardCore.GEN_PARAM_STATE:
                if (direction == ProjectionWizardCore.PREVIOUS_STATE) {
                    if (((pdata.getSourceType() == SourceType.CORPUS || pdata.getSourceType() == SourceType.BIBTEX) && pdata.getDissimilarityType() != DissimilarityType.KOLMOGOROV) || pdata.getSourceType() == SourceType.POINTS) {
                        //general parameters -> dimension reduction
                        currentState = ProjectionWizardCore.DIMEN_RED_STATE;
                        dimensionReductionView.reset();
                        nextWizard = dimensionReductionView;
                    } else {
                        //general parameters -> projection+distance
                        currentState = ProjectionWizardCore.PROJ_DIST_STATE;
                        projDistView.reset();
                        nextWizard = projDistView;
                    }
                }
                
                if (direction == ProjectionWizardCore.NEXT_STATE) {
                    projView = ProjectionFactory.getInstance(pdata.getProjectionType()).getProjectionView(pdata);
                    currentState = ProjectionWizardCore.PROJECT_STATE;
                    projView.reset();
                    nextWizard = projView;
                }
                
                break;

            case ProjectionWizardCore.PROJECT_STATE:
                if (direction == ProjectionWizardCore.PREVIOUS_STATE) {
                    // idmap -> general parameters
                    currentState = ProjectionWizardCore.GEN_PARAM_STATE;
                    generalParametersView.reset();
                    nextWizard = generalParametersView;
                }
                
                if (direction == ProjectionWizardCore.NEXT_STATE) {
                    if (projView != null) {
                        builder = new GraphBuilder(projView, graph);
                        builder.start();
                    }
                }
                
                break;
        }

        return nextWizard;
    }

    public void stopProcess() {
        if (builder != null) {
            builder.stop();
        }
    }
}
