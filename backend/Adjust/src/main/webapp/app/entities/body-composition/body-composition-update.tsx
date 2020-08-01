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

  const { bodyCompositionFile, bodyCompositionFileContentType, bloodTestFile, bloodTestFileContentType } = bodyCompositionEntity;

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
