/*
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * The Original Code is "Simplenlg".
 *
 * The Initial Developer of the Original Code is Ehud Reiter, Albert Gatt and Dave Westwater.
 * Portions created by Ehud Reiter, Albert Gatt and Dave Westwater are Copyright (C) 2010-11 The University of Aberdeen. All Rights Reserved.
 *
 * Contributor(s): Ehud Reiter, Albert Gatt, Dave Wewstwater, Roman Kutlak, Margaret Mitchell, Saad Mahamood.
 */
package simplenlg.lexicon.english;

import org.junit.Assert;
import simplenlg.features.Inflection;
import simplenlg.features.LexicalFeature;
import simplenlg.framework.LexicalCategory;
import simplenlg.framework.WordElement;
import simplenlg.lexicon.Lexicon;

/**
 * @author Dave Westwater, Data2Text Ltd
 */
public class SharedLexiconTests {

    public void doBasicTests(Lexicon lexicon) {
        // test getWords. Should be 2 "can" (of any cat), 1 noun tree, 0 adj
        // trees
        Assert.assertEquals(3, lexicon.getWords("can").size());
        Assert.assertEquals(1, lexicon.getWords("can", LexicalCategory.NOUN).size());
        Assert.assertEquals(0, lexicon.getWords("can", LexicalCategory.ADJECTIVE).size());

        // below test removed as standard morph variants no longer recorded in
        // lexicon
        // WordElement early = lexicon.getWord("early",
        // LexicalCategory.ADJECTIVE);
        // Assert.assertEquals("earlier",
        // early.getFeatureAsString(Feature.COMPARATIVE));

        // test getWord. Comparative of ADJ "good" is "better", superlative is
        // "best", this is a qualitative and predicative adjective
        WordElement good = lexicon.getWord("good", LexicalCategory.ADJECTIVE);
        Assert.assertEquals("better", good.getFeatureAsString(LexicalFeature.COMPARATIVE));
        Assert.assertEquals("best", good.getFeatureAsString(LexicalFeature.SUPERLATIVE));
        Assert.assertTrue(good.getFeatureAsBoolean(LexicalFeature.QUALITATIVE));
        Assert.assertTrue(good.getFeatureAsBoolean(LexicalFeature.PREDICATIVE));
        Assert.assertFalse(good.getFeatureAsBoolean(LexicalFeature.COLOUR));
        Assert.assertFalse(good.getFeatureAsBoolean(LexicalFeature.CLASSIFYING));

        // test getWord. There is only one "woman", and its plural is "women".
        // It is not an acronym, not proper, and countable
        WordElement woman = lexicon.getWord("woman");

        Assert.assertEquals("women", woman.getFeatureAsString(LexicalFeature.PLURAL));
        Assert.assertNull(woman.getFeatureAsString(LexicalFeature.ACRONYM_OF));
        Assert.assertFalse(woman.getFeatureAsBoolean(LexicalFeature.PROPER));
        Assert.assertFalse(woman.hasInflectionalVariant(Inflection.UNCOUNT));

        // NB: This fails if the lexicon is XMLLexicon. No idea why.
        // Assert.assertEquals("irreg",
        // woman.getFeatureAsString(LexicalFeature.DEFAULT_INFL));

        // test getWord. Noun "sand" is non-count
        WordElement sand = lexicon.getWord("sand", LexicalCategory.NOUN);
        Assert.assertTrue(sand.hasInflectionalVariant(Inflection.UNCOUNT));
        Assert.assertEquals(Inflection.UNCOUNT, sand.getDefaultInflectionalVariant());

        // test hasWord
        Assert.assertTrue(lexicon.hasWord("tree")); // "tree" exists
        Assert.assertFalse(lexicon.hasWord("tree", LexicalCategory.ADVERB)); // but not as an adverb

        // test getWordByID; quickly, also check that this is a verb_modifier
        WordElement quickly = lexicon.getWordByID("E0051632");
        Assert.assertEquals("quickly", quickly.getBaseForm());
        Assert.assertEquals(LexicalCategory.ADVERB, quickly.getCategory());
        Assert.assertTrue(quickly.getFeatureAsBoolean(LexicalFeature.VERB_MODIFIER));
        Assert.assertFalse(quickly.getFeatureAsBoolean(LexicalFeature.SENTENCE_MODIFIER));
        Assert.assertFalse(quickly.getFeatureAsBoolean(LexicalFeature.INTENSIFIER));

        // test getWordFromVariant, verb type (tran or intran, not ditran)
        WordElement eat = lexicon.getWordFromVariant("eating");
        Assert.assertEquals("eat", eat.getBaseForm());
        Assert.assertEquals(LexicalCategory.VERB, eat.getCategory());
        Assert.assertTrue(eat.getFeatureAsBoolean(LexicalFeature.INTRANSITIVE));
        Assert.assertTrue(eat.getFeatureAsBoolean(LexicalFeature.TRANSITIVE));
        Assert.assertFalse(eat.getFeatureAsBoolean(LexicalFeature.DITRANSITIVE));

        // test BE is handled OK
        Assert.assertEquals(
                "been",
                lexicon.getWordFromVariant("is", LexicalCategory.VERB).getFeatureAsString(LexicalFeature.PAST_PARTICIPLE)
        );

        // test modal
        WordElement can = lexicon.getWord("can", LexicalCategory.MODAL);
        Assert.assertEquals("could", can.getFeatureAsString(LexicalFeature.PAST));

        // test non-existent word
        Assert.assertEquals(0, lexicon.getWords("akjmchsgk").size());

        // test lookup word method
        Assert.assertEquals(lexicon.lookupWord("say", LexicalCategory.VERB).getBaseForm(), "say");
        Assert.assertEquals(lexicon.lookupWord("said", LexicalCategory.VERB).getBaseForm(), "say");
        Assert.assertEquals(lexicon.lookupWord("E0054448", LexicalCategory.VERB).getBaseForm(), "say");
    }
}