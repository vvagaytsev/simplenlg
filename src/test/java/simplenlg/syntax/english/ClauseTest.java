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

package simplenlg.syntax.english;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import simplenlg.features.*;
import simplenlg.framework.*;
import simplenlg.phrasespec.*;

// TODO: Auto-generated Javadoc

/**
 * The Class STest.
 */
public class ClauseTest extends SimpleNLG4Test {

    // set up a few more fixtures
    /**
     * The s4.
     */
    SPhraseSpec s1, s2, s3, s4;

    /**
     * Instantiates a new s test.
     *
     * @param name the name
     */
    public ClauseTest(String name) {
        super(name);
    }

    /*
     * (non-Javadoc)
     *
     * @see simplenlg.test.SimplenlgTest#setUp()
     */
    @Override
    @Before
    protected void setUp() {
        super.setUp();

        // the woman kisses the man
        this.s1 = this.phraseFactory.createClause();
        this.s1.setSubject(this.woman);
        this.s1.setVerbPhrase(this.kiss);
        this.s1.setObject(this.man);

        // there is the dog on the rock
        this.s2 = this.phraseFactory.createClause();
        this.s2.setSubject("there");
        this.s2.setVerb("be");
        this.s2.setObject(this.dog);
        this.s2.addPostModifier(this.onTheRock);

        // the man gives the woman John's flower
        this.s3 = this.phraseFactory.createClause();
        this.s3.setSubject(this.man);
        this.s3.setVerbPhrase(this.give);

        NPPhraseSpec flower = this.phraseFactory.createNounPhrase("flower");
        NPPhraseSpec john = this.phraseFactory.createNounPhrase("John");
        john.setFeature(Feature.POSSESSIVE, true);
        flower.setFeature(InternalFeature.SPECIFIER, john);
        this.s3.setObject(flower);
        this.s3.setIndirectObject(this.woman);

        this.s4 = this.phraseFactory.createClause();
        this.s4.setFeature(Feature.CUE_PHRASE, "however");
        this.s4.addFrontModifier("tomorrow");

        CoordinatedPhraseElement subject = this.phraseFactory
                .createCoordinatedPhrase(this.phraseFactory
                        .createNounPhrase("Jane"), this.phraseFactory
                        .createNounPhrase("Andrew"));

        this.s4.setSubject(subject);

        PhraseElement pick = this.phraseFactory.createVerbPhrase("pick up");
        this.s4.setVerbPhrase(pick);
        this.s4.setObject("the balls");
        this.s4.addPostModifier("in the shop");
        this.s4.setFeature(Feature.TENSE, Tense.FUTURE);
    }

    @After
    public void tearDown() {
        super.tearDown();

        this.s1 = null;
        this.s2 = null;
        this.s3 = null;
        this.s4 = null;
    }


    /**
     * Initial test for basic sentences.
     */
    @Test
    public void testBasic() {
        Assert.assertEquals("the woman kisses the man", this.realiser.realise(this.s1).getRealisation());
        Assert.assertEquals("there is the dog on the rock", this.realiser.realise(this.s2).getRealisation());

        setUp();
        Assert.assertEquals(
                "the man gives the woman John's flower",
                this.realiser.realise(this.s3).getRealisation()
        );
        Assert.assertEquals(
                "however tomorrow Jane and Andrew will pick up the balls in the shop",
                this.realiser.realise(this.s4).getRealisation()
        );
    }

    /**
     * Test did not.
     */
    @Test
    public void testDidNot() {
        PhraseElement s = phraseFactory.createClause("John", "eat");
        s.setFeature(Feature.TENSE, Tense.PAST);
        s.setFeature(Feature.NEGATED, true);
        Assert.assertEquals("John did not eat", this.realiser.realise(s).getRealisation());
    }

    /**
     * Test did not.
     */
    @Test
    public void testVPNegation() {
        // negate the VP
        PhraseElement vp = phraseFactory.createVerbPhrase("lie");
        vp.setFeature(Feature.TENSE, Tense.PAST);
        vp.setFeature(Feature.NEGATED, true);
        PhraseElement compl = phraseFactory.createVerbPhrase("etherize");
        compl.setFeature(Feature.TENSE, Tense.PAST);
        vp.setComplement(compl);

        SPhraseSpec s = phraseFactory.createClause(phraseFactory.createNounPhrase("the", "patient"), vp);
        Assert.assertEquals("the patient did not lie etherized", this.realiser.realise(s).getRealisation());
    }

    /**
     * Test that pronominal args are being correctly cast as NPs.
     */
    @Test
    public void testPronounArguments() {
        // the subject of s2 should have been cast into a pronominal NP
        NLGElement subj = this.s2.getFeatureAsElementList(InternalFeature.SUBJECTS).get(0);
        Assert.assertTrue(subj.isA(PhraseCategory.NOUN_PHRASE));
        // Assert.assertTrue(LexicalCategory.PRONOUN.equals(((PhraseElement)
        // subj)
        // .getCategory()));
    }

    /**
     * Tests for setting tense, aspect and passive from the sentence interface.
     */
    @Test
    public void testTenses() {
        // simple past
        this.s3.setFeature(Feature.TENSE, Tense.PAST);
        Assert.assertEquals(
                "the man gave the woman John's flower",
                this.realiser.realise(this.s3).getRealisation()
        );

        // perfect
        this.s3.setFeature(Feature.PERFECT, true);
        Assert.assertEquals(
                "the man had given the woman John's flower",
                this.realiser.realise(this.s3).getRealisation()
        );

        // negation
        this.s3.setFeature(Feature.NEGATED, true);
        Assert.assertEquals(
                "the man had not given the woman John's flower",
                this.realiser.realise(this.s3).getRealisation()
        );

        this.s3.setFeature(Feature.PROGRESSIVE, true);
        Assert.assertEquals(
                "the man had not been giving the woman John's flower",
                this.realiser.realise(this.s3).getRealisation()
        );

        // passivisation with direct and indirect object
        this.s3.setFeature(Feature.PASSIVE, true);
        // Assert.assertEquals(
        //				"John's flower had not been being given the woman by the man",
        // this.realiser.realise(this.s3).getRealisation());
    }

    /**
     * Test what happens when a sentence is subordinated as complement of a verb.
     */
    @Test
    public void testSubordination() {
        // subordinate sentence by setting it as complement of a verb
        this.say.addComplement(this.s3);

        // check the getter
        Assert.assertEquals(ClauseStatus.SUBORDINATE, this.s3.getFeature(InternalFeature.CLAUSE_STATUS));

        // check realisation
        Assert.assertEquals(
                "says that the man gives the woman John's flower",
                this.realiser.realise(this.say).getRealisation()
        );
    }

    /**
     * Test the various forms of a sentence, including subordinates.
     */
    /**
     *
     */
    @Test
    public void testForm() {

        // check the getter method
        Assert.assertEquals(Form.NORMAL, this.s1.getFeatureAsElement(InternalFeature.VERB_PHRASE).getFeature(Feature.FORM));

        // infinitive
        this.s1.setFeature(Feature.FORM, Form.INFINITIVE);
        Assert.assertEquals("to kiss the man", this.realiser.realise(this.s1).getRealisation());

        // gerund with "there"
        this.s2.setFeature(Feature.FORM, Form.GERUND);
        Assert.assertEquals(
                "there being the dog on the rock",
                this.realiser.realise(this.s2).getRealisation()
        );

        // gerund with possessive
        this.s3.setFeature(Feature.FORM, Form.GERUND);
        Assert.assertEquals(
                "the man's giving the woman John's flower",
                this.realiser.realise(this.s3).getRealisation()
        );

        // imperative
        this.s3.setFeature(Feature.FORM, Form.IMPERATIVE);

        Assert.assertEquals(
                "give the woman John's flower",
                this.realiser.realise(this.s3).getRealisation()
        );

        // subordinating the imperative to a verb should turn it to infinitive
        this.say.addComplement(this.s3);

        Assert.assertEquals(
                "says to give the woman John's flower",
                this.realiser.realise(this.say).getRealisation()
        );

        // imperative -- case II
        this.s4.setFeature(Feature.FORM, Form.IMPERATIVE);
        Assert.assertEquals(
                "however tomorrow pick up the balls in the shop",
                this.realiser.realise(this.s4).getRealisation()
        );

        // infinitive -- case II
        this.s4 = this.phraseFactory.createClause();
        this.s4.setFeature(Feature.CUE_PHRASE, "however");
        this.s4.addFrontModifier("tomorrow");

        CoordinatedPhraseElement subject = new CoordinatedPhraseElement(
                this.phraseFactory.createNounPhrase("Jane"),
                this.phraseFactory.createNounPhrase("Andrew")
        );

        this.s4.setFeature(InternalFeature.SUBJECTS, subject);

        PhraseElement pick = this.phraseFactory.createVerbPhrase("pick up");
        this.s4.setFeature(InternalFeature.VERB_PHRASE, pick);
        this.s4.setObject("the balls");
        this.s4.addPostModifier("in the shop");
        this.s4.setFeature(Feature.TENSE, Tense.FUTURE);
        this.s4.setFeature(Feature.FORM, Form.INFINITIVE);
        Assert.assertEquals(
                "however to pick up the balls in the shop tomorrow",
                this.realiser.realise(this.s4).getRealisation()
        );
    }

    /**
     * Slightly more complex tests for forms.
     */
    @Test
    public void testForm2() {
        // set s4 as subject of a new sentence
        SPhraseSpec temp = this.phraseFactory.createClause(this.s4, "be", "recommended");

        Assert.assertEquals(
                "however tomorrow Jane and Andrew's picking up the " +
                        "balls in the shop is recommended",
                this.realiser.realise(temp).getRealisation()
        );

        // compose this with a new sentence
        // ER - switched direct and indirect object in sentence
        SPhraseSpec temp2 = this.phraseFactory.createClause("I", "tell", temp);
        temp2.setFeature(Feature.TENSE, Tense.FUTURE);

        PhraseElement indirectObject = this.phraseFactory.createNounPhrase("John");

        temp2.setIndirectObject(indirectObject);

        Assert.assertEquals(
                "I will tell John that however tomorrow Jane and " +
                        "Andrew's picking up the balls in the shop is " +
                        "recommended",
                this.realiser.realise(temp2).getRealisation()
        );

        // turn s4 to imperative and put it in indirect object position

        this.s4 = this.phraseFactory.createClause();
        this.s4.setFeature(Feature.CUE_PHRASE, "however");
        this.s4.addFrontModifier("tomorrow");

        CoordinatedPhraseElement subject = new CoordinatedPhraseElement(
                this.phraseFactory.createNounPhrase("Jane"),
                this.phraseFactory.createNounPhrase("Andrew")
        );

        this.s4.setSubject(subject);

        PhraseElement pick = this.phraseFactory.createVerbPhrase("pick up");
        this.s4.setVerbPhrase(pick);
        this.s4.setObject("the balls");
        this.s4.addPostModifier("in the shop");
        this.s4.setFeature(Feature.TENSE, Tense.FUTURE);
        this.s4.setFeature(Feature.FORM, Form.IMPERATIVE);

        temp2 = this.phraseFactory.createClause("I", "tell", this.s4);
        indirectObject = this.phraseFactory.createNounPhrase("John");
        temp2.setIndirectObject(indirectObject);
        temp2.setFeature(Feature.TENSE, Tense.FUTURE);

        Assert.assertEquals(
                "I will tell John however to pick up the balls in the shop tomorrow",
                this.realiser.realise(temp2).getRealisation()
        );
    }

    /**
     * Tests for gerund forms and genitive subjects.
     */
    @Test
    public void testGerundsubject() {
        // the man's giving the woman John's flower upset Peter
        SPhraseSpec _s4 = this.phraseFactory.createClause();
        _s4.setVerbPhrase(this.phraseFactory.createVerbPhrase("upset"));
        _s4.setFeature(Feature.TENSE, Tense.PAST);
        _s4.setObject(this.phraseFactory.createNounPhrase("Peter"));
        this.s3.setFeature(Feature.PERFECT, true);

        // set the sentence as subject of another: makes it a gerund
        _s4.setSubject(this.s3);

        // suppress the genitive realisation of the NP subject in gerund
        // sentences
        this.s3.setFeature(Feature.SUPPRESS_GENITIVE_IN_GERUND, true);

        // check the realisation: subject should not be genitive
        Assert.assertEquals(
                "the man having given the woman John's flower upset Peter",
                this.realiser.realise(_s4).getRealisation()
        );

    }

    /**
     * Some tests for multiple embedded sentences.
     */
    @Test
    public void testComplexSentence1() {
        setUp();
        // the man's giving the woman John's flower upset Peter
        SPhraseSpec complexS = this.phraseFactory.createClause();
        complexS.setVerbPhrase(this.phraseFactory.createVerbPhrase("upset"));
        complexS.setFeature(Feature.TENSE, Tense.PAST);
        complexS.setObject(this.phraseFactory.createNounPhrase("Peter"));
        this.s3.setFeature(Feature.PERFECT, true);
        complexS.setSubject(this.s3);

        // check the realisation: subject should be genitive
        Assert.assertEquals(
                "the man's having given the woman John's flower upset Peter",
                this.realiser.realise(complexS).getRealisation()
        );

        setUp();
        // coordinate sentences in subject position
        SPhraseSpec s5 = this.phraseFactory.createClause();
        s5.setSubject(this.phraseFactory.createNounPhrase("some", "person"));
        s5.setVerbPhrase(this.phraseFactory.createVerbPhrase("stroke"));
        s5.setObject(this.phraseFactory.createNounPhrase("the", "cat"));

        CoordinatedPhraseElement coord = new CoordinatedPhraseElement(this.s3,
                s5);
        complexS = this.phraseFactory.createClause();
        complexS.setVerbPhrase(this.phraseFactory.createVerbPhrase("upset"));
        complexS.setFeature(Feature.TENSE, Tense.PAST);
        complexS.setObject(this.phraseFactory.createNounPhrase("Peter"));
        complexS.setSubject(coord);
        this.s3.setFeature(Feature.PERFECT, true);

        Assert.assertEquals(
                "the man's having given the woman John's flower "
                        + "and some person's stroking the cat upset Peter",
                this.realiser.realise(complexS).getRealisation()
        );

        setUp();
        // now subordinate the complex sentence
        // coord.setClauseStatus(SPhraseSpec.ClauseType.MAIN);
        SPhraseSpec s6 = this.phraseFactory.createClause();
        s6.setVerbPhrase(this.phraseFactory.createVerbPhrase("tell"));
        s6.setFeature(Feature.TENSE, Tense.PAST);
        s6.setSubject(this.phraseFactory.createNounPhrase("the", "boy"));
        // ER - switched indirect and direct object
        PhraseElement indirect = this.phraseFactory.createNounPhrase("every", "girl");
        s6.setIndirectObject(indirect);
        complexS = this.phraseFactory.createClause();
        complexS.setVerbPhrase(this.phraseFactory.createVerbPhrase("upset"));
        complexS.setFeature(Feature.TENSE, Tense.PAST);
        complexS.setObject(this.phraseFactory.createNounPhrase("Peter"));
        s6.setObject(complexS);
        coord = new CoordinatedPhraseElement(this.s3, s5);
        complexS.setSubject(coord);
        this.s3.setFeature(Feature.PERFECT, true);
        Assert.assertEquals(
                "the boy told every girl that the man's having given the woman "
                        + "John's flower and some person's stroking the cat "
                        + "upset Peter",
                this.realiser.realise(s6).getRealisation()
        );
    }

    /**
     * More coordination tests.
     */
    @Test
    public void testComplexSentence3() {
        setUp();

        this.s1 = this.phraseFactory.createClause();
        this.s1.setSubject(this.woman);
        this.s1.setVerb("kiss");
        this.s1.setObject(this.man);

        PhraseElement _man = this.phraseFactory.createNounPhrase("the", "man");
        this.s3 = this.phraseFactory.createClause();
        this.s3.setSubject(_man);
        this.s3.setVerb("give");

        NPPhraseSpec flower = this.phraseFactory.createNounPhrase("flower");
        NPPhraseSpec john = this.phraseFactory.createNounPhrase("John");
        john.setFeature(Feature.POSSESSIVE, true);
        flower.setSpecifier(john);
        this.s3.setObject(flower);

        PhraseElement _woman = this.phraseFactory.createNounPhrase("the", "woman");
        this.s3.setIndirectObject(_woman);

        // the coordinate sentence allows us to raise and lower complementiser
        CoordinatedPhraseElement coord2 = new CoordinatedPhraseElement(this.s1, this.s3);
        coord2.setFeature(Feature.TENSE, Tense.PAST);

        this.realiser.setDebugMode(true);
        Assert.assertEquals(
                "the woman kissed the man and the man gave the woman John's flower",
                this.realiser.realise(coord2).getRealisation()
        );
    }

    // /**
    // * Sentence with clausal subject with verb "be" and a progressive feature
    // */
    // public void testComplexSentence2() {
    // SPhraseSpec subject = this.phraseFactory.createClause(
    // this.phraseFactory.createNounPhrase("the", "child"),
    // this.phraseFactory.createVerbPhrase("be"), this.phraseFactory
    // .createWord("difficult", LexicalCategory.ADJECTIVE));
    // subject.setFeature(Feature.PROGRESSIVE, true);
    // }

    /**
     * Tests recogition of strings in API.
     */
    @Test
    public void testStringRecognition() {

        // test recognition of forms of "be"
        PhraseElement _s1 = this.phraseFactory.createClause("my cat", "be", "sad");
        Assert.assertEquals("my cat is sad", this.realiser.realise(_s1).getRealisation());

        // test recognition of pronoun for afreement
        PhraseElement _s2 = this.phraseFactory.createClause("I", "want", "Mary");

        Assert.assertEquals("I want Mary", this.realiser.realise(_s2).getRealisation());

        // test recognition of pronoun for correct form
        PhraseElement subject = this.phraseFactory.createNounPhrase("dog");
        subject.setFeature(InternalFeature.SPECIFIER, "a");
        subject.addPostModifier("from next door");
        PhraseElement object = this.phraseFactory.createNounPhrase("I");
        PhraseElement s = this.phraseFactory.createClause(subject, "chase", object);
        s.setFeature(Feature.PROGRESSIVE, true);
        Assert.assertEquals("a dog from next door is chasing me", this.realiser.realise(s).getRealisation());
    }

    /**
     * Tests complex agreement.
     */
    @Test
    public void testAgreement() {

        // basic agreement
        NPPhraseSpec np = this.phraseFactory.createNounPhrase("dog");
        np.setSpecifier("the");
        np.addPreModifier("angry");
        PhraseElement _s1 = this.phraseFactory.createClause(np, "chase", "John");
        Assert.assertEquals("the angry dog chases John", this.realiser.realise(_s1).getRealisation());

        // plural
        np = this.phraseFactory.createNounPhrase("dog");
        np.setSpecifier("the");
        np.addPreModifier("angry");
        np.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
        _s1 = this.phraseFactory.createClause(np, "chase", "John");
        Assert.assertEquals("the angry dogs chase John", this.realiser.realise(_s1).getRealisation());

        // test agreement with "there is"
        np = this.phraseFactory.createNounPhrase("dog");
        np.addPreModifier("angry");
        np.setFeature(Feature.NUMBER, NumberAgreement.SINGULAR);
        np.setSpecifier("a");
        PhraseElement _s2 = this.phraseFactory.createClause("there", "be", np);
        Assert.assertEquals("there is an angry dog", this.realiser.realise(_s2).getRealisation());

        // plural with "there"
        np = this.phraseFactory.createNounPhrase("dog");
        np.addPreModifier("angry");
        np.setSpecifier("a");
        np.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
        _s2 = this.phraseFactory.createClause("there", "be", np);
        Assert.assertEquals("there are some angry dogs", this.realiser.realise(_s2).getRealisation());
    }

    /**
     * Tests passive.
     */
    @Test
    public void testPassive() {
        // passive with just complement
        SPhraseSpec _s1 = this.phraseFactory.createClause(
                null,
                "intubate",
                this.phraseFactory.createNounPhrase("the", "baby")
        );
        _s1.setFeature(Feature.PASSIVE, true);

        Assert.assertEquals(
                "the baby is intubated",
                this.realiser.realise(_s1).getRealisation()
        );

        // passive with subject and complement
        _s1 = this.phraseFactory.createClause(
                null,
                "intubate",
                this.phraseFactory.createNounPhrase("the", "baby")
        );
        _s1.setSubject(this.phraseFactory.createNounPhrase("the nurse"));
        _s1.setFeature(Feature.PASSIVE, true);
        Assert.assertEquals(
                "the baby is intubated by the nurse",
                this.realiser.realise(_s1).getRealisation()
        );

        // passive with subject and indirect object
        SPhraseSpec _s2 = this.phraseFactory.createClause(
                null,
                "give",
                this.phraseFactory.createNounPhrase("the", "baby")
        );

        PhraseElement morphine = this.phraseFactory.createNounPhrase("50ug of morphine");
        _s2.setIndirectObject(morphine);
        _s2.setFeature(Feature.PASSIVE, true);
        Assert.assertEquals(
                "the baby is given 50ug of morphine",
                this.realiser.realise(_s2).getRealisation()
        );

        // passive with subject, complement and indirect object
        _s2 = this.phraseFactory.createClause(
                this.phraseFactory.createNounPhrase("the", "nurse"),
                "give",
                this.phraseFactory.createNounPhrase("the", "baby")
        );

        morphine = this.phraseFactory.createNounPhrase("50ug of morphine");
        _s2.setIndirectObject(morphine);
        _s2.setFeature(Feature.PASSIVE, true);
        Assert.assertEquals(
                "the baby is given 50ug of morphine by the nurse",
                this.realiser.realise(_s2).getRealisation()
        );

        // test agreement in passive
        PhraseElement _s3 = this.phraseFactory.createClause(
                new CoordinatedPhraseElement("my dog", "your cat"),
                "chase",
                "George"
        );
        _s3.setFeature(Feature.TENSE, Tense.PAST);
        _s3.addFrontModifier("yesterday");
        Assert.assertEquals(
                "yesterday my dog and your cat chased George",
                this.realiser.realise(_s3).getRealisation()
        );

        _s3 = this.phraseFactory.createClause(
                new CoordinatedPhraseElement("my dog", "your cat"),
                "chase",
                this.phraseFactory.createNounPhrase("George")
        );
        _s3.setFeature(Feature.TENSE, Tense.PAST);
        _s3.addFrontModifier("yesterday");
        _s3.setFeature(Feature.PASSIVE, true);
        Assert.assertEquals(
                "yesterday George was chased by my dog and your cat",
                this.realiser.realise(_s3).getRealisation()
        );

        // test correct pronoun forms
        PhraseElement _s4 = this.phraseFactory.createClause(
                this.phraseFactory.createNounPhrase("he"),
                "chase",
                this.phraseFactory.createNounPhrase("I")
        );
        Assert.assertEquals(
                "he chases me",
                this.realiser.realise(_s4).getRealisation()
        );
        _s4 = this.phraseFactory.createClause(
                this.phraseFactory.createNounPhrase("he"),
                "chase",
                this.phraseFactory.createNounPhrase("me")
        );
        _s4.setFeature(Feature.PASSIVE, true);
        Assert.assertEquals("I am chased by him", this.realiser.realise(_s4).getRealisation());

        // same thing, but giving the S constructor "me". Should recognise
        // correct pro
        // anyway
        PhraseElement _s5 = this.phraseFactory.createClause("him", "chase", "I");
        Assert.assertEquals("he chases me", this.realiser.realise(_s5).getRealisation());

        _s5 = this.phraseFactory.createClause("him", "chase", "I");
        _s5.setFeature(Feature.PASSIVE, true);
        Assert.assertEquals("I am chased by him", this.realiser.realise(_s5).getRealisation());
    }

    /**
     * Test that complements set within the VP are raised when sentence is
     * passivised.
     */
    @Test
    public void testPassiveWithInternalVPComplement() {
        PhraseElement vp = this.phraseFactory.createVerbPhrase(
                phraseFactory.createWord("upset", LexicalCategory.VERB)
        );
        vp.addComplement(phraseFactory.createNounPhrase("the", "man"));
        PhraseElement _s6 = this.phraseFactory.createClause(phraseFactory.createNounPhrase("the", "child"), vp);
        _s6.setFeature(Feature.TENSE, Tense.PAST);
        Assert.assertEquals("the child upset the man", this.realiser.realise(_s6).getRealisation());

        _s6.setFeature(Feature.PASSIVE, true);
        Assert.assertEquals("the man was upset by the child", this.realiser.realise(_s6).getRealisation());
    }

    /**
     * Tests tenses with modals.
     */
    @Test
    public void testModal() {

        setUp();
        // simple modal in present tense
        this.s3.setFeature(Feature.MODAL, "should");
        Assert.assertEquals("the man should give the woman John's flower",
                this.realiser.realise(this.s3).getRealisation()
        );

        // modal + future -- uses present
        setUp();
        this.s3.setFeature(Feature.MODAL, "should");
        this.s3.setFeature(Feature.TENSE, Tense.FUTURE);
        Assert.assertEquals("the man should give the woman John's flower",
                this.realiser.realise(this.s3).getRealisation()
        );

        // modal + present progressive
        setUp();
        this.s3.setFeature(Feature.MODAL, "should");
        this.s3.setFeature(Feature.TENSE, Tense.FUTURE);
        this.s3.setFeature(Feature.PROGRESSIVE, true);
        Assert.assertEquals("the man should be giving the woman John's flower",
                this.realiser.realise(this.s3).getRealisation()
        );

        // modal + past tense
        setUp();
        this.s3.setFeature(Feature.MODAL, "should");
        this.s3.setFeature(Feature.TENSE, Tense.PAST);
        Assert.assertEquals(
                "the man should have given the woman John's flower",
                this.realiser.realise(this.s3).getRealisation()
        );

        // modal + past progressive
        setUp();
        this.s3.setFeature(Feature.MODAL, "should");
        this.s3.setFeature(Feature.TENSE, Tense.PAST);
        this.s3.setFeature(Feature.PROGRESSIVE, true);

        Assert.assertEquals(
                "the man should have been giving the woman John's flower",
                this.realiser.realise(this.s3).getRealisation()
        );
    }

    /**
     * Test for passivisation with modals.
     */
    @Test
    public void testModalWithPassive() {
        NPPhraseSpec object = this.phraseFactory.createNounPhrase("the", "pizza");
        AdjPhraseSpec post = this.phraseFactory.createAdjectivePhrase("good");
        AdvPhraseSpec as = this.phraseFactory.createAdverbPhrase("as");
        as.addComplement(post);
        VPPhraseSpec verb = this.phraseFactory.createVerbPhrase("classify");
        verb.addPostModifier(as);
        verb.addComplement(object);
        SPhraseSpec s = this.phraseFactory.createClause();
        s.setVerbPhrase(verb);
        s.setFeature(Feature.MODAL, "can");
        // s.setFeature(Feature.FORM, Form.INFINITIVE);
        s.setFeature(Feature.PASSIVE, true);
        Assert.assertEquals("the pizza can be classified as good", this.realiser.realise(s).getRealisation());
    }

    @Test
    public void testPassiveWithPPCompl() {
        // passive with just complement
        NPPhraseSpec subject = this.phraseFactory.createNounPhrase("wave");
        subject.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
        NPPhraseSpec object = this.phraseFactory.createNounPhrase("surfer");
        object.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);

        SPhraseSpec _s1 = this.phraseFactory.createClause(subject, "carry", object);

        // add a PP complement
        PPPhraseSpec pp = this.phraseFactory.createPrepositionPhrase(
                "to",
                this.phraseFactory.createNounPhrase("the", "shore")
        );
        pp.setFeature(InternalFeature.DISCOURSE_FUNCTION, DiscourseFunction.INDIRECT_OBJECT);
        _s1.addComplement(pp);

        _s1.setFeature(Feature.PASSIVE, true);

        Assert.assertEquals(
                "surfers are carried to the shore by waves",
                this.realiser.realise(_s1).getRealisation()
        );
    }

    @Test
    public void testPassiveWithPPMod() {
        // passive with just complement
        NPPhraseSpec subject = this.phraseFactory.createNounPhrase("wave");
        subject.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
        NPPhraseSpec object = this.phraseFactory.createNounPhrase("surfer");
        object.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);

        SPhraseSpec _s1 = this.phraseFactory.createClause(subject, "carry", object);

        // add a PP complement
        PPPhraseSpec pp = this.phraseFactory.createPrepositionPhrase(
                "to",
                this.phraseFactory.createNounPhrase("the", "shore")
        );
        _s1.addPostModifier(pp);

        _s1.setFeature(Feature.PASSIVE, true);

        Assert.assertEquals(
                "surfers are carried to the shore by waves",
                this.realiser.realise(_s1).getRealisation()
        );
    }

    @Test
    public void testCuePhrase() {
        NPPhraseSpec subject = this.phraseFactory.createNounPhrase("wave");
        subject.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);
        NPPhraseSpec object = this.phraseFactory.createNounPhrase("surfer");
        object.setFeature(Feature.NUMBER, NumberAgreement.PLURAL);

        SPhraseSpec _s1 = this.phraseFactory.createClause(subject, "carry", object);

        // add a PP complement
        PPPhraseSpec pp = this.phraseFactory.createPrepositionPhrase(
                "to",
                this.phraseFactory.createNounPhrase("the", "shore")
        );
        _s1.addPostModifier(pp);
        _s1.setFeature(Feature.PASSIVE, true);
        _s1.addFrontModifier("however");


        //without comma separation of cue phrase
        Assert.assertEquals(
                "however surfers are carried to the shore by waves",
                this.realiser.realise(_s1).getRealisation()
        );

        //with comma separation
        this.realiser.setCommaSepCuephrase(true);
        Assert.assertEquals(
                "however, surfers are carried to the shore by waves",
                this.realiser.realise(_s1).getRealisation()
        );
    }

    /**
     * Check that setComplement replaces earlier complements.
     */
    @Test
    public void setComplementTest() {
        SPhraseSpec s = this.phraseFactory.createClause();
        s.setSubject("I");
        s.setVerb("see");

        s.setObject("a dog");
        Assert.assertEquals("I see a dog", this.realiser.realise(s).getRealisation());

        s.setObject("a cat");
        Assert.assertEquals("I see a cat", this.realiser.realise(s).getRealisation());

        s.setObject("a wolf");
        Assert.assertEquals("I see a wolf", this.realiser.realise(s).getRealisation());
    }


    /**
     * Test for subclauses involving WH-complements Based on a query by Owen
     * Bennett
     */
    @Test
    public void subclausesTest() {
        // Once upon a time, there was an Accountant, called Jeff, who lived in a forest.

        // main sentence
        NPPhraseSpec acct = this.phraseFactory.createNounPhrase("a", "accountant");

        // first postmodifier of "an accountant"
        VPPhraseSpec sub1 = this.phraseFactory.createVerbPhrase("call");
        sub1.addComplement("Jeff");
        sub1.setFeature(Feature.FORM, Form.PAST_PARTICIPLE);
        // this is an appositive modifier, which makes simplenlg put it between
        // commas
        sub1.setFeature(Feature.APPOSITIVE, true);
        acct.addPostModifier(sub1);

        // second postmodifier of "an accountant" is "who lived in a forest"
        SPhraseSpec sub2 = this.phraseFactory.createClause();
        VPPhraseSpec subvp = this.phraseFactory.createVerbPhrase("live");
        subvp.setFeature(Feature.TENSE, Tense.PAST);
        subvp.setComplement(this.phraseFactory.createPrepositionPhrase(
                "in",
                this.phraseFactory.createNounPhrase("a", "forest"))
        );
        sub2.setVerbPhrase(subvp);
        // simplenlg can't yet handle wh-clauses in NPs, so we need to hack it
        // by setting the subject to "who"
        sub2.setSubject("who");
        acct.addPostModifier(sub2);

        // main sentence
        SPhraseSpec s = this.phraseFactory.createClause("there", "be", acct);
        s.setFeature(Feature.TENSE, Tense.PAST);

        // add front modifier "once upon a time"
        s.addFrontModifier("once upon a time");

        Assert.assertEquals(
                "once upon a time there was an accountant, called Jeff, who lived in a forest",
                this.realiser.realise(s).getRealisation()
        );
    }
}