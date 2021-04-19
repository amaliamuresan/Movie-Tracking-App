import 'package:built_collection/built_collection.dart';
import 'package:built_value/built_value.dart';
import 'package:built_value/serializer.dart';
import 'package:movie_app/models/actor.dart';
import 'package:movie_app/models/serializers.dart';

part 'movie.g.dart';

abstract class Movie implements Built<Movie, MovieBuilder> {
  factory Movie([void Function(MovieBuilder) updates]) = _$Movie;

  factory Movie.fromJson(dynamic json) {
    return serializers.deserializeWith(serializer, json);
  }

  Movie._();

  @nullable
  String get id;

  @nullable
  String get title;

  @nullable
  int get year;

  @nullable
  num get rating;

  @nullable
  int get runtime;

  @nullable
  BuiltList<String> get genres;

  @nullable
  String get summary;

  @nullable
  String get poster_path;

  @nullable
  String get backdrop_path;

  @nullable
  String get overview;

  @nullable
  String get director;

  @nullable
  String get imdbRating;

  @nullable
  BuiltList<Actor> get actors;

  Map<String, dynamic> get json => serializers.serializeWith(serializer, this) as Map<String, dynamic>;
  static Serializer<Movie> get serializer => _$movieSerializer;
}