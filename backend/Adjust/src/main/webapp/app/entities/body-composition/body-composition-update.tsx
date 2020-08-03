import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, setFileData, openFile, byteSize, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAdjustProgram } from 'app/shared/model/adjust-program.model';
import { getEntities as getAdjustPrograms } from 'app/entities/adjust-program/adjust-program.reducer';
import { getEntity, updateEntity, createEntity, setBlob, reset } from './body-composition.reducer';
import { IBodyComposition } from 'app/shared/model/body-composition.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IBodyCompositionUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const BodyCompositionUpdate = (props: IBodyCompositionUpdateProps) => {
  const [programId, setProgramId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { bodyCompositionEntity, adjustPrograms, loading, updating } = props;

  const {
    bodyImage,
    bodyImageContentType,
    bodyCompositionFile,
    bodyCompositionFileContentType,
    bloodTestFile,
    bloodTestFileContentType,
  } = bodyCompositionEntity;

  const handleClose = () => {
    props.history.push('/body-composition');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getAdjustPrograms();
  }, []);

  const onBlobChange = (isAnImage, name) => event => {
    setFileData(event, (contentType, data) => props.setBlob(name, data, contentType), isAnImage);
  };

  const clearBlob = name => () => {
    props.setBlob(name, undefined, undefined);
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...bodyCompositionEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="adjustApp.bodyComposition.home.createOrEditLabel">
            <Translate contentKey="adjustApp.bodyComposition.home.createOrEditLabel">Create or edit a BodyComposition</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : bodyCompositionEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="body-composition-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="body-composition-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="createdAtLabel" for="body-composition-createdAt">
                  <Translate contentKey="adjustApp.bodyComposition.createdAt">Created At</Translate>
                </Label>
                <AvField id="body-composition-createdAt" type="date" className="form-control" name="createdAt" />
              </AvGroup>
              <AvGroup>
                <Label id="heightLabel" for="body-composition-height">
                  <Translate contentKey="adjustApp.bodyComposition.height">Height</Translate>
                </Label>
                <AvField id="body-composition-height" type="string" className="form-control" name="height" />
              </AvGroup>
              <AvGroup>
                <Label id="weightLabel" for="body-composition-weight">
                  <Translate contentKey="adjustApp.bodyComposition.weight">Weight</Translate>
                </Label>
                <AvField id="body-composition-weight" type="string" className="form-control" name="weight" />
              </AvGroup>
              <AvGroup>
                <Label id="bmiLabel" for="body-composition-bmi">
                  <Translate contentKey="adjustApp.bodyComposition.bmi">Bmi</Translate>
                </Label>
                <AvField id="body-composition-bmi" type="string" className="form-control" name="bmi" />
              </AvGroup>
              <AvGroup>
                <Label id="wristLabel" for="body-composition-wrist">
                  <Translate contentKey="adjustApp.bodyComposition.wrist">Wrist</Translate>
                </Label>
                <AvField id="body-composition-wrist" type="string" className="form-control" name="wrist" />
              </AvGroup>
              <AvGroup>
                <Label id="waistLabel" for="body-composition-waist">
                  <Translate contentKey="adjustApp.bodyComposition.waist">Waist</Translate>
                </Label>
                <AvField id="body-composition-waist" type="string" className="form-control" name="waist" />
              </AvGroup>
              <AvGroup>
                <Label id="lbmLabel" for="body-composition-lbm">
                  <Translate contentKey="adjustApp.bodyComposition.lbm">Lbm</Translate>
                </Label>
                <AvField id="body-composition-lbm" type="string" className="form-control" name="lbm" />
              </AvGroup>
              <AvGroup>
                <Label id="muscleMassLabel" for="body-composition-muscleMass">
                  <Translate contentKey="adjustApp.bodyComposition.muscleMass">Muscle Mass</Translate>
                </Label>
                <AvField id="body-composition-muscleMass" type="string" className="form-control" name="muscleMass" />
              </AvGroup>
              <AvGroup>
                <Label id="muscleMassPercentageLabel" for="body-composition-muscleMassPercentage">
                  <Translate contentKey="adjustApp.bodyComposition.muscleMassPercentage">Muscle Mass Percentage</Translate>
                </Label>
                <AvField id="body-composition-muscleMassPercentage" type="string" className="form-control" name="muscleMassPercentage" />
              </AvGroup>
              <AvGroup>
                <Label id="fatMassLabel" for="body-composition-fatMass">
                  <Translate contentKey="adjustApp.bodyComposition.fatMass">Fat Mass</Translate>
                </Label>
                <AvField id="body-composition-fatMass" type="string" className="form-control" name="fatMass" />
              </AvGroup>
              <AvGroup>
                <Label id="fatMassPercentageLabel" for="body-composition-fatMassPercentage">
                  <Translate contentKey="adjustApp.bodyComposition.fatMassPercentage">Fat Mass Percentage</Translate>
                </Label>
                <AvField id="body-composition-fatMassPercentage" type="string" className="form-control" name="fatMassPercentage" />
              </AvGroup>
              <AvGroup>
                <Label id="genderLabel" for="body-composition-gender">
                  <Translate contentKey="adjustApp.bodyComposition.gender">Gender</Translate>
                </Label>
                <AvInput
                  id="body-composition-gender"
                  type="select"
                  className="form-control"
                  name="gender"
                  value={(!isNew && bodyCompositionEntity.gender) || 'MALE'}
                >
                  <option value="MALE">{translate('adjustApp.Gender.MALE')}</option>
                  <option value="FEMALE">{translate('adjustApp.Gender.FEMALE')}</option>
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label id="ageLabel" for="body-composition-age">
                  <Translate contentKey="adjustApp.bodyComposition.age">Age</Translate>
                </Label>
                <AvField id="body-composition-age" type="string" className="form-control" name="age" />
              </AvGroup>
              <AvGroup>
                <AvGroup>
                  <Label id="bodyImageLabel" for="bodyImage">
                    <Translate contentKey="adjustApp.bodyComposition.bodyImage">Body Image</Translate>
                  </Label>
                  <br />
                  {bodyImage ? (
                    <div>
                      {bodyImageContentType ? (
                        <a onClick={openFile(bodyImageContentType, bodyImage)}>
                          <img src={`data:${bodyImageContentType};base64,${bodyImage}`} style={{ maxHeight: '100px' }} />
                        </a>
                      ) : null}
                      <br />
                      <Row>
                        <Col md="11">
                          <span>
                            {bodyImageContentType}, {byteSize(bodyImage)}
                          </span>
                        </Col>
                        <Col md="1">
                          <Button color="danger" onClick={clearBlob('bodyImage')}>
                            <FontAwesomeIcon icon="times-circle" />
                          </Button>
                        </Col>
                      </Row>
                    </div>
                  ) : null}
                  <input id="file_bodyImage" type="file" onChange={onBlobChange(true, 'bodyImage')} accept="image/*" />
                  <AvInput type="hidden" name="bodyImage" value={bodyImage} />
                </AvGroup>
              </AvGroup>
              <AvGroup>
                <AvGroup>
                  <Label id="bodyCompositionFileLabel" for="bodyCompositionFile">
                    <Translate contentKey="adjustApp.bodyComposition.bodyCompositionFile">Body Composition File</Translate>
                  </Label>
                  <br />
                  {bodyCompositionFile ? (
                    <div>
                      {bodyCompositionFileContentType ? (
                        <a onClick={openFile(bodyCompositionFileContentType, bodyCompositionFile)}>
                          <img
                            src={`data:${bodyCompositionFileContentType};base64,${bodyCompositionFile}`}
                            style={{ maxHeight: '100px' }}
                          />
                        </a>
                      ) : null}
                      <br />
                      <Row>
                        <Col md="11">
                          <span>
                            {bodyCompositionFileContentType}, {byteSize(bodyCompositionFile)}
                          </span>
                        </Col>
                        <Col md="1">
                          <Button color="danger" onClick={clearBlob('bodyCompositionFile')}>
                            <FontAwesomeIcon icon="times-circle" />
                          </Button>
                        </Col>
                      </Row>
                    </div>
                  ) : null}
                  <input id="file_bodyCompositionFile" type="file" onChange={onBlobChange(true, 'bodyCompositionFile')} accept="image/*" />
                  <AvInput type="hidden" name="bodyCompositionFile" value={bodyCompositionFile} />
                </AvGroup>
              </AvGroup>
              <AvGroup>
                <AvGroup>
                  <Label id="bloodTestFileLabel" for="bloodTestFile">
                    <Translate contentKey="adjustApp.bodyComposition.bloodTestFile">Blood Test File</Translate>
                  </Label>
                  <br />
                  {bloodTestFile ? (
                    <div>
                      {bloodTestFileContentType ? (
                        <a onClick={openFile(bloodTestFileContentType, bloodTestFile)}>
                          <img src={`data:${bloodTestFileContentType};base64,${bloodTestFile}`} style={{ maxHeight: '100px' }} />
                        </a>
                      ) : null}
                      <br />
                      <Row>
                        <Col md="11">
                          <span>
                            {bloodTestFileContentType}, {byteSize(bloodTestFile)}
                          </span>
                        </Col>
                        <Col md="1">
                          <Button color="danger" onClick={clearBlob('bloodTestFile')}>
                            <FontAwesomeIcon icon="times-circle" />
                          </Button>
                        </Col>
                      </Row>
                    </div>
                  ) : null}
                  <input id="file_bloodTestFile" type="file" onChange={onBlobChange(true, 'bloodTestFile')} accept="image/*" />
                  <AvInput type="hidden" name="bloodTestFile" value={bloodTestFile} />
                </AvGroup>
              </AvGroup>
              <AvGroup>
                <Label for="body-composition-program">
                  <Translate contentKey="adjustApp.bodyComposition.program">Program</Translate>
                </Label>
                <AvInput id="body-composition-program" type="select" className="form-control" name="programId">
                  <option value="" key="0" />
                  {adjustPrograms
                    ? adjustPrograms.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/body-composition" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  adjustPrograms: storeState.adjustProgram.entities,
  bodyCompositionEntity: storeState.bodyComposition.entity,
  loading: storeState.bodyComposition.loading,
  updating: storeState.bodyComposition.updating,
  updateSuccess: storeState.bodyComposition.updateSuccess,
});

const mapDispatchToProps = {
  getAdjustPrograms,
  getEntity,
  updateEntity,
  setBlob,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(BodyCompositionUpdate);
