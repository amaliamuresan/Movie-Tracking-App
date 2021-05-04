library app_user;

import 'package:built_collection/built_collection.dart';
import 'package:built_value/built_value.dart';
import 'package:built_value/serializer.dart';
import 'package:movie_app/models/serializers.dart';

part 'app_user.g.dart';

abstract class AppUser implements Built<AppUser, AppUserBuilder>{

  factory AppUser([void Function(AppUserBuilder b) updates]) = _$AppUser;

  factory AppUser.fromJson(dynamic json) => serializers.deserializeWith(serializer, json);

  AppUser._();

  @nullable
  String get uid;

  @nullable
  String get token;

  @nullable
  String get email;

  @nullable
  String get password;

  @nullable
  @BuiltValueField(wireName: 'display_name')
  String get displayName;

  @nullable
  @BuiltValueField(wireName: 'followed_users')
  BuiltList<String> get friends;

  @nullable
  @BuiltValueField(wireName: 'to_watch_movies')
  BuiltList<String> get toWatchMovies;

  @nullable
  @BuiltValueField(wireName: 'watched_movies')
  BuiltList<String> get watchedMovies;

  Map<String, dynamic> get json => serializers.serializeWith(serializer, this) as Map<String, dynamic>;
  static Serializer<AppUser> get serializer => _$appUserSerializer;

}