/*
 * Copyright (C) 2011 Marco Aurélio Graciotto Silva <magsilva@ironiacorp.com>
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package visualizer.textprocessing;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import lode.miner.PipelineUtil;
import lode.miner.TypicalPipelineComponent;
import lode.model.Element;
import lode.model.text.TextResource;

import visualizer.corpus.Corpus;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class NgramReducerFilter extends TypicalPipelineComponent
{
	private Multiset<PexNgramResource> resources;
	
	private String separator = Corpus.NGRAM_SEPARATOR;
	
	private int n;
	
	public NgramReducerFilter()
	{
		resources = HashMultiset.create();
	}

	public void processPexNgramResource(PexNgramResource resource)
	{
		String text = resource.getText();
		int n = text.split(separator).length;
		if (n > this.n) {
			this.n = n;
		}
		resources.add(resource);
	}
	
	public void stop()
	{
		Set<PexNgramResource> wordsToRemove = new HashSet<PexNgramResource>();
		
		for (int i = n; i > 1; i--) {
			Iterator<PexNgramResource> iBag = resources.iterator();
			while (iBag.hasNext()) {
				PexNgramResource ngramTextResource = iBag.next();
				String ngramText = ngramTextResource.getText();
				String[] ngramWords = ngramText.split(separator);
				if (ngramWords.length == i) {
					for (String ngramWord : ngramWords) {
						Ngram ngramToIgnore = new Ngram(ngramWord);
						PexNgramResource ngramToIgnoreResource = new PexNgramResource(ngramToIgnore); 
						int count = resources.count(ngramToIgnoreResource);
						if (count == 1) {
							wordsToRemove.add(ngramToIgnoreResource);
						} else {
							resources.setCount(ngramToIgnoreResource, count - 1);
						}
					}
				}
			}
		}
		
		Iterator<PexNgramResource> i = resources.iterator();
		while (i.hasNext()) {
			PexNgramResource resource = i.next();
			if (! wordsToRemove.contains(resource)) {
				deliver(resource);
			}
		}
	}
	
	
	@Override
	public void process(Element resource)
	{
		if (resource instanceof TextResource) {
			PexNgramResource pexNgramResource = (PexNgramResource) resource;
			processPexNgramResource(pexNgramResource);
		}
	}
	

	public Class<? extends Element>[] getInputResourceTypes() {
		return PipelineUtil.getResourceTypes(PexNgramResource.class);
	}

	public Class<? extends Element>[] getOutputResourceTypes() {
		return PipelineUtil.getResourceTypes(PexNgramResource.class);
	}
}
