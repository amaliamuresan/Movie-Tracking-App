// GENERATED CODE - DO NOT MODIFY BY HAND
// ignore_for_file: deprecated_member_use, deprecated_member_use_from_same_package, use_function_type_syntax_for_parameters, unnecessary_const, avoid_init_to_null, invalid_override_different_default_values_named, prefer_expression_function_bodies

part of 'register.dart';

// **************************************************************************
// FreezedGenerator
// **************************************************************************

T _$identity<T>(T value) => value;

/// @nodoc
class _$RegisterTearOff {
  const _$RegisterTearOff();

// ignore: unused_element
  Register$ call(
      {@required String email,
      @required String password,
      @required String displayName,
      @required void Function(AppAction) response}) {
    return Register$(
      email: email,
      password: password,
      displayName: displayName,
      response: response,
    );
  }

// ignore: unused_element
  RegisterSuccessful successful(AppUser appUser) {
    return RegisterSuccessful(
      appUser,
    );
  }

// ignore: unused_element
  RegisterError error(dynamic error) {
    return RegisterError(
      error,
    );
  }
}

/// @nodoc
// ignore: unused_element
const $Register = _$RegisterTearOff();

/// @nodoc
mixin _$Register {
  @optionalTypeArgs
  TResult when<TResult extends Object>(
    TResult $default(String email, String password, String displayName,
        void Function(AppAction) response), {
    @required TResult successful(AppUser appUser),
    @required TResult error(dynamic error),
  });
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object>(
    TResult $default(String email, String password, String displayName,
        void Function(AppAction) response), {
    TResult successful(AppUser appUser),
    TResult error(dynamic error),
    @required TResult orElse(),
  });
  @optionalTypeArgs
  TResult map<TResult extends Object>(
    TResult $default(Register$ value), {
    @required TResult successful(RegisterSuccessful value),
    @required TResult error(RegisterError value),
  });
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object>(
    TResult $default(Register$ value), {
    TResult successful(RegisterSuccessful value),
    TResult error(RegisterError value),
    @required TResult orElse(),
  });
}

/// @nodoc
abstract class $RegisterCopyWith<$Res> {
  factory $RegisterCopyWith(Register value, $Res Function(Register) then) =
      _$RegisterCopyWithImpl<$Res>;
}

/// @nodoc
class _$RegisterCopyWithImpl<$Res> implements $RegisterCopyWith<$Res> {
  _$RegisterCopyWithImpl(this._value, this._then);

  final Register _value;
  // ignore: unused_field
  final $Res Function(Register) _then;
}

/// @nodoc
abstract class $Register$CopyWith<$Res> {
  factory $Register$CopyWith(Register$ value, $Res Function(Register$) then) =
      _$Register$CopyWithImpl<$Res>;
  $Res call(
      {String email,
      String password,
      String displayName,
      void Function(AppAction) response});
}

/// @nodoc
class _$Register$CopyWithImpl<$Res> extends _$RegisterCopyWithImpl<$Res>
    implements $Register$CopyWith<$Res> {
  _$Register$CopyWithImpl(Register$ _value, $Res Function(Register$) _then)
      : super(_value, (v) => _then(v as Register$));

  @override
  Register$ get _value => super._value as Register$;

  @override
  $Res call({
    Object email = freezed,
    Object password = freezed,
    Object displayName = freezed,
    Object response = freezed,
  }) {
    return _then(Register$(
      email: email == freezed ? _value.email : email as String,
      password: password == freezed ? _value.password : password as String,
      displayName:
          displayName == freezed ? _value.displayName : displayName as String,
      response: response == freezed
          ? _value.response
          : response as void Function(AppAction),
    ));
  }
}

/// @nodoc
class _$Register$ implements Register$ {
  const _$Register$(
      {@required this.email,
      @required this.password,
      @required this.displayName,
      @required this.response})
      : assert(email != null),
        assert(password != null),
        assert(displayName != null),
        assert(response != null);

  @override
  final String email;
  @override
  final String password;
  @override
  final String displayName;
  @override
  final void Function(AppAction) response;

  @override
  String toString() {
    return 'Register(email: $email, password: $password, displayName: $displayName, response: $response)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other is Register$ &&
            (identical(other.email, email) ||
                const DeepCollectionEquality().equals(other.email, email)) &&
            (identical(other.password, password) ||
                const DeepCollectionEquality()
                    .equals(other.password, password)) &&
            (identical(other.displayName, displayName) ||
                const DeepCollectionEquality()
                    .equals(other.displayName, displayName)) &&
            (identical(other.response, response) ||
                const DeepCollectionEquality()
                    .equals(other.response, response)));
  }

  @override
  int get hashCode =>
      runtimeType.hashCode ^
      const DeepCollectionEquality().hash(email) ^
      const DeepCollectionEquality().hash(password) ^
      const DeepCollectionEquality().hash(displayName) ^
      const DeepCollectionEquality().hash(response);

  @JsonKey(ignore: true)
  @override
  $Register$CopyWith<Register$> get copyWith =>
      _$Register$CopyWithImpl<Register$>(this, _$identity);

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object>(
    TResult $default(String email, String password, String displayName,
        void Function(AppAction) response), {
    @required TResult successful(AppUser appUser),
    @required TResult error(dynamic error),
  }) {
    assert($default != null);
    assert(successful != null);
    assert(error != null);
    return $default(email, password, displayName, response);
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object>(
    TResult $default(String email, String password, String displayName,
        void Function(AppAction) response), {
    TResult successful(AppUser appUser),
    TResult error(dynamic error),
    @required TResult orElse(),
  }) {
    assert(orElse != null);
    if ($default != null) {
      return $default(email, password, displayName, response);
    }
    return orElse();
  }

  @override
  @optionalTypeArgs
  TResult map<TResult extends Object>(
    TResult $default(Register$ value), {
    @required TResult successful(RegisterSuccessful value),
    @required TResult error(RegisterError value),
  }) {
    assert($default != null);
    assert(successful != null);
    assert(error != null);
    return $default(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object>(
    TResult $default(Register$ value), {
    TResult successful(RegisterSuccessful value),
    TResult error(RegisterError value),
    @required TResult orElse(),
  }) {
    assert(orElse != null);
    if ($default != null) {
      return $default(this);
    }
    return orElse();
  }
}

abstract class Register$ implements Register {
  const factory Register$(
      {@required String email,
      @required String password,
      @required String displayName,
      @required void Function(AppAction) response}) = _$Register$;

  String get email;
  String get password;
  String get displayName;
  void Function(AppAction) get response;
  @JsonKey(ignore: true)
  $Register$CopyWith<Register$> get copyWith;
}

/// @nodoc
abstract class $RegisterSuccessfulCopyWith<$Res> {
  factory $RegisterSuccessfulCopyWith(
          RegisterSuccessful value, $Res Function(RegisterSuccessful) then) =
      _$RegisterSuccessfulCopyWithImpl<$Res>;
  $Res call({AppUser appUser});
}

/// @nodoc
class _$RegisterSuccessfulCopyWithImpl<$Res>
    extends _$RegisterCopyWithImpl<$Res>
    implements $RegisterSuccessfulCopyWith<$Res> {
  _$RegisterSuccessfulCopyWithImpl(
      RegisterSuccessful _value, $Res Function(RegisterSuccessful) _then)
      : super(_value, (v) => _then(v as RegisterSuccessful));

  @override
  RegisterSuccessful get _value => super._value as RegisterSuccessful;

  @override
  $Res call({
    Object appUser = freezed,
  }) {
    return _then(RegisterSuccessful(
      appUser == freezed ? _value.appUser : appUser as AppUser,
    ));
  }
}

/// @nodoc
class _$RegisterSuccessful implements RegisterSuccessful {
  const _$RegisterSuccessful(this.appUser) : assert(appUser != null);

  @override
  final AppUser appUser;

  @override
  String toString() {
    return 'Register.successful(appUser: $appUser)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other is RegisterSuccessful &&
            (identical(other.appUser, appUser) ||
                const DeepCollectionEquality().equals(other.appUser, appUser)));
  }

  @override
  int get hashCode =>
      runtimeType.hashCode ^ const DeepCollectionEquality().hash(appUser);

  @JsonKey(ignore: true)
  @override
  $RegisterSuccessfulCopyWith<RegisterSuccessful> get copyWith =>
      _$RegisterSuccessfulCopyWithImpl<RegisterSuccessful>(this, _$identity);

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object>(
    TResult $default(String email, String password, String displayName,
        void Function(AppAction) response), {
    @required TResult successful(AppUser appUser),
    @required TResult error(dynamic error),
  }) {
    assert($default != null);
    assert(successful != null);
    assert(error != null);
    return successful(appUser);
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object>(
    TResult $default(String email, String password, String displayName,
        void Function(AppAction) response), {
    TResult successful(AppUser appUser),
    TResult error(dynamic error),
    @required TResult orElse(),
  }) {
    assert(orElse != null);
    if (successful != null) {
      return successful(appUser);
    }
    return orElse();
  }

  @override
  @optionalTypeArgs
  TResult map<TResult extends Object>(
    TResult $default(Register$ value), {
    @required TResult successful(RegisterSuccessful value),
    @required TResult error(RegisterError value),
  }) {
    assert($default != null);
    assert(successful != null);
    assert(error != null);
    return successful(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object>(
    TResult $default(Register$ value), {
    TResult successful(RegisterSuccessful value),
    TResult error(RegisterError value),
    @required TResult orElse(),
  }) {
    assert(orElse != null);
    if (successful != null) {
      return successful(this);
    }
    return orElse();
  }
}

abstract class RegisterSuccessful implements Register {
  const factory RegisterSuccessful(AppUser appUser) = _$RegisterSuccessful;

  AppUser get appUser;
  @JsonKey(ignore: true)
  $RegisterSuccessfulCopyWith<RegisterSuccessful> get copyWith;
}

/// @nodoc
abstract class $RegisterErrorCopyWith<$Res> {
  factory $RegisterErrorCopyWith(
          RegisterError value, $Res Function(RegisterError) then) =
      _$RegisterErrorCopyWithImpl<$Res>;
  $Res call({dynamic error});
}

/// @nodoc
class _$RegisterErrorCopyWithImpl<$Res> extends _$RegisterCopyWithImpl<$Res>
    implements $RegisterErrorCopyWith<$Res> {
  _$RegisterErrorCopyWithImpl(
      RegisterError _value, $Res Function(RegisterError) _then)
      : super(_value, (v) => _then(v as RegisterError));

  @override
  RegisterError get _value => super._value as RegisterError;

  @override
  $Res call({
    Object error = freezed,
  }) {
    return _then(RegisterError(
      error == freezed ? _value.error : error as dynamic,
    ));
  }
}

@Implements(ErrorAction)

/// @nodoc
class _$RegisterError implements RegisterError {
  const _$RegisterError(this.error) : assert(error != null);

  @override
  final dynamic error;

  @override
  String toString() {
    return 'Register.error(error: $error)';
  }

  @override
  bool operator ==(dynamic other) {
    return identical(this, other) ||
        (other is RegisterError &&
            (identical(other.error, error) ||
                const DeepCollectionEquality().equals(other.error, error)));
  }

  @override
  int get hashCode =>
      runtimeType.hashCode ^ const DeepCollectionEquality().hash(error);

  @JsonKey(ignore: true)
  @override
  $RegisterErrorCopyWith<RegisterError> get copyWith =>
      _$RegisterErrorCopyWithImpl<RegisterError>(this, _$identity);

  @override
  @optionalTypeArgs
  TResult when<TResult extends Object>(
    TResult $default(String email, String password, String displayName,
        void Function(AppAction) response), {
    @required TResult successful(AppUser appUser),
    @required TResult error(dynamic error),
  }) {
    assert($default != null);
    assert(successful != null);
    assert(error != null);
    return error(this.error);
  }

  @override
  @optionalTypeArgs
  TResult maybeWhen<TResult extends Object>(
    TResult $default(String email, String password, String displayName,
        void Function(AppAction) response), {
    TResult successful(AppUser appUser),
    TResult error(dynamic error),
    @required TResult orElse(),
  }) {
    assert(orElse != null);
    if (error != null) {
      return error(this.error);
    }
    return orElse();
  }

  @override
  @optionalTypeArgs
  TResult map<TResult extends Object>(
    TResult $default(Register$ value), {
    @required TResult successful(RegisterSuccessful value),
    @required TResult error(RegisterError value),
  }) {
    assert($default != null);
    assert(successful != null);
    assert(error != null);
    return error(this);
  }

  @override
  @optionalTypeArgs
  TResult maybeMap<TResult extends Object>(
    TResult $default(Register$ value), {
    TResult successful(RegisterSuccessful value),
    TResult error(RegisterError value),
    @required TResult orElse(),
  }) {
    assert(orElse != null);
    if (error != null) {
      return error(this);
    }
    return orElse();
  }
}

abstract class RegisterError implements Register, ErrorAction {
  const factory RegisterError(dynamic error) = _$RegisterError;

  dynamic get error;
  @JsonKey(ignore: true)
  $RegisterErrorCopyWith<RegisterError> get copyWith;
}
