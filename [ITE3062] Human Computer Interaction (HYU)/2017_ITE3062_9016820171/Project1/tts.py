import json
from os.path import join, dirname
from watson_developer_cloud import SpeechToTextV1, ToneAnalyzerV3

speech_to_text = SpeechToTextV1(
    username="2243d3c8-dbf0-46c3-b6d5-ea9109a544c4",
    password="aJvIcdRLb75v",
    x_watson_learning_opt_out=False
)

tone_analyzer = ToneAnalyzerV3(
    username="2c1c98c2-0d12-413d-8de9-c0a22658a5c1",
    password= "fyZkDZDjnbCd",
    version='2016-02-11')


def analyze():
    with open(join(dirname(__file__), 'recording.wav'),
              'rb') as audio_file:
        # print(json.dumps(speech_to_text.recognize(
        #     audio_file, content_type='audio/wav', timestamps=True,
        #     word_confidence=True),
        #     indent=2))
        results = json.dumps(speech_to_text.recognize(audio_file, content_type='audio/wav'))
        test = json.loads(results)
        voice_text = test["results"][0]["alternatives"][0]["transcript"]
        print("Sentence is: "+voice_text)

        tone_results = json.dumps(tone_analyzer.tone(text=voice_text), indent=2)
        tone_test = json.loads(tone_results)

        anger_score = tone_test["document_tone"]["tone_categories"][0]["tones"][0]["score"]*100
        disgust_score = tone_test["document_tone"]["tone_categories"][0]["tones"][1]["score"]*100
        fear_score = tone_test["document_tone"]["tone_categories"][0]["tones"][2]["score"]*100
        joy_score = tone_test["document_tone"]["tone_categories"][0]["tones"][3]["score"]*100
        sadness_score = tone_test["document_tone"]["tone_categories"][0]["tones"][4]["score"]*100

        print 'Anger:{:.2f}% \nDisgust:{:.2f}% \nFear:{:.2f}% \nJoy:{:.2f}% \nSadness:{:.2f}%\n'.format(anger_score, disgust_score, fear_score, joy_score, sadness_score)
        return "{},{:.2f},{:.2f},{:.2f},{:.2f},{:.2f}\n".format(voice_text, anger_score, disgust_score, fear_score, joy_score, sadness_score)
