Muzk
===========

[![Build Status](https://travis-ci.com/albrzykowski/muzk.svg?branch=master)](https://travis-ci.com/albrzykowski/muzk)

Muzk is computer aided music composition library

## Instalation
You can install Muzk using Maven or Gradle. For more information about muzka package visit: https://github.com/albrzykowski/muzk/packages/261539

## Usage
### Sequences
Sequences follow concept of [serialism](https://en.wikipedia.org/wiki/Serialism).
#### Random Walk Sequence
```
import io.github.albrzykowski.muzk.{RandomAdapter => Random}
import package io.github.albrzykowski.muzk.sequence.RandomWalkSequence
// ...
val seq = List("C", "E", "G", "F#", "B")
random = Random
val randomWalkSequence = new RandomWalkSequence(random)
val result = randomWalkSequence.generate(seq, 4)
```
should return something like:
```
List("G", "F#", "G", "C")
```
#### Random Permutation Sequence
```
import package io.github.albrzykowski.muzk.sequence.RandomPermutationSeqence
// ...
val seq = List("C", "E", "G", "F#", "B")
val randomPermutationSequence = new RandomPermutationSequence()
val result = randomPermutationSequence.generate(seq, 4)
```
should return something like:
```
List("E", "F#", "C", "B", "G")
```
#### Retrograde Sequence
```
import package io.github.albrzykowski.muzk.sequence.RetrogradeSequence
// ...
val seq = List("C", "E", "G")
val retrogradeSequence= new RetrogradeSequence()
val result = retrogradeSequence.generate(seq)
```
should return:
```
List("G", "E", "C")
```
### Composers
Composers combines `Sequences` generators to create music pieces. 
#### Random Walk Composer
```
import io.github.albrzykowski.muzk.{RandomAdapter => Random}
import io.github.albrzykowski.muzk.composer.RandomWalkComposer
// ...
val pitches = List("C", "D", "E", "F", "G", "A", "B")
val values = List("Whole note", "Half note")
val types = List("Note", "Rest")

random = Random
val randomWalkComposer = new RandomWalkComposer(random)
```
should return something like:
```
List(
    ("E", "Whole note", "Note"),
    ("D", "Half note", "Rest")
    ("C", "Whole note", "Note")
    ("D", "Half note", "Rest")
    ("E", "Whole note", "Note")
    ("F", "Half note", "Rest")
    ("G", "Whole note", "Note")
)
```